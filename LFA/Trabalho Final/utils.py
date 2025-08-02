from constants import *

def set_if_false(automaton, key, property, newValue):
    if not automaton[key][PROPS].get(property):
        automaton[key][PROPS][property] = newValue

def is_final(automaton, state):
    return automaton[state][PROPS][FINAL] == True

def init_automaton(model):
    automaton = dict()
    for line in model:
        tokens = line.strip().split()
        # skip empty lines
        if not tokens:
            continue
        fromStateWithMarker = tokens[0].strip()
        fromState = fromStateWithMarker.replace("*", "").replace(">", "")
        automaton.setdefault(fromState, dict())
        automaton[fromState].setdefault(SYMBOLS, dict())
        automaton[fromState].setdefault(PROPS, dict())
        # check if the state is an INITIAL or FINAL state based on the '>' and '*' markers
        # if these properties are already TRUE, do not override them when processing other lines
        set_if_false(automaton, fromState, FINAL, str.startswith(fromStateWithMarker, "*"))
        set_if_false(automaton, fromState, INITIAL, str.startswith(fromStateWithMarker, ">"))

        # check if the state has any transitions and if so, include them in the automaton
        if len(tokens) >= 2:
            symbol = tokens[1]
            toState = tokens[2]
            pop = tokens[3]
            push = tokens[4]
            automaton[fromState][SYMBOLS].setdefault(symbol, [])
            automaton[fromState][SYMBOLS][symbol].append((toState, pop, push))

    return automaton

# returns the item at the top of the stack without
# popping it from the stack
def peek(stack):
    if len(stack) == 0:
        return None
    return stack[len(stack) - 1]

def apply_transition(transition_tuple, stack):
    # anything to pop off the stack?
    pop = transition_tuple[1]
    # anything to push into the stack?
    push = transition_tuple[2]
    if (pop != EPSILON and pop != QUESTION_MARK):
        # before popping it, check if the item
        # matches the top of the stack
        if (peek(stack) == pop):
            stack.pop()
        else:
            return CANNOT_APPLY_TRANSITION
    if (push != EPSILON and push != QUESTION_MARK):
        stack.append(push)
    return APPLIED_TRANSITION

def get_initial_state(automaton):
    for state in automaton:
        if automaton[state][PROPS][INITIAL] == True:
            return state

def get_word(input):
    # check if input is None or blank
    if input is None or not input.split():
        return None
    # check if input is a commented line (starts with #)
    if str.startswith(input, "#"):
        return None
    # otherwise, strip blank spaces and return
    return input.strip()