from constants import *
from utils import get_initial_state

TEMPLATE_PATH = "template/index.html"
# The serializer will replace the following string in the template file
# to insert the generated DOT digraphs in the HTML
TEMPLATE_PLACEHOLDER = "//PLACEHOLDER"

def write_output(title, html):
    with open(title + ".html", 'w') as f:
        f.write(html)

def read_template():
    with open(TEMPLATE_PATH) as f:
        return "\n".join(f.readlines())
    
def save_multiple(automata, words, paths, global_state, title):
    stages = []
    for i, _ in enumerate(words):
        # we want to display the status AFTER the cycle completes
        status = global_state[i - 1] if i >= 1 else None
        stages.extend(to_dot(automata[i], words[i], paths[i], status))
    template = read_template()
    quoted_stages = []
    for stage in stages:
        quoted_stages.append(f"`{stage}`")
    output_string = ",\n".join(quoted_stages)
    html = template.replace(TEMPLATE_PLACEHOLDER, output_string)
    write_output(title, html)

def save(automata, word, path, title):
    stages = to_dot(automata, word, path, None)
    template = read_template()
    quoted_stages = []
    for stage in stages:
        quoted_stages.append(f"`{stage}`")
    output_string = ",\n".join(quoted_stages)
    html = template.replace(TEMPLATE_PLACEHOLDER, output_string)
    write_output(title, html)

def node(name, final, active):
    shape = "doublecircle" if final else "circle"
    color = "red" if active else "black"
    return f"node [node_id = {name} shape = {shape} color = {color} fontcolor = {color}]; {name};"

def transition(from_node, symbol, transition, active):
    to_node = transition[0]
    pop = transition[1]
    push = transition[2]
    color = "red" if active else "black"
    return f"{from_node} -> {to_node} [edge_id = \"{from_node},{to_node},{symbol},{pop},{push}\" label = \"{symbol}, {pop}, {push}\" color = {color} fontcolor = {color} fontsize = 20]"

def label(tape, stack, global_state):
    status = str(global_state) if global_state is not None else ""
    return f"label = <<b>PILHA:</b><br/>{stack}<br/><b>FITA:</b><br/>{tape}<br/><b>STATUS:</b><br/>{status}>;"

def is_active(key, to_node, initial_state, step):
    return key == to_node or (key == initial_state and step is None)

def digraph(automata, word, global_state, stage):
    from_node = stage[0] if stage else None
    to_node = stage[1] if stage else None
    symbol = stage[2] if stage else None

    # initial state color
    initial_state_color = "red" if not stage else "black"

    # find the initial state
    initial_state = get_initial_state(automata)

    # build title
    title_node = f"node [shape = point]; arrow;"

    # build nodes
    nodes = [node(key, automata[key][PROPS][FINAL], is_active(key, to_node, initial_state, stage)) for key in automata.keys()]
    nodes = "\n".join(nodes) + "\n"

    # build initial transition
    start_arrow = f"arrow -> {initial_state} [color = {initial_state_color}];\n"

    # build transitions
    transitions = set() # prevents duplicate transitions
    for key in automata.keys():
        for s in automata[key][SYMBOLS]:
            for t in automata[key][SYMBOLS][s]:
                active = key == from_node and s == symbol and t[0] == to_node
                transitions.add(transition(key, s, t, active))
    transitions = "\n".join(transitions) + "\n"

    tape = stage[3] if stage else word.split()
    stack = stage[4] if stage else []
    tape_stack_label = label(tape, stack, global_state)

    return "digraph G {\nrankdir=LR fontsize=30\n" + \
        title_node + \
        nodes + \
        start_arrow + \
        transitions + \
        tape_stack_label + "}"

def to_dot(automata, word, path, global_state):
    # display initial automata
    yield digraph(automata, word, global_state, None)
    # display transitions for each stage in the path
    for stage in path:
        yield digraph(automata, word, global_state, stage)