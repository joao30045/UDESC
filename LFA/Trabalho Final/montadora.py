############################################################
#### Authors: GIANLUCA RIGHETTO
####          JOAO PEDRO GARCIA GUEDES
#### PDA (pushdown automata) Simulator
#### This project is an exercise implementation to process
#### pushdown-down automata, that is, automata with
#### stack-based memory. It works with deterministic- (DFA),
#### non-deterministic- (NFA) and epsilon- (NFe) automata.
############################################################

from serialization import save_multiple
from utils import *
from constants import *
from automaton import *
import sys
import os

global_stack = []

# the automaton implementation supports OUTPUT,
# when a word is successfully validated, it will call
# this function, which can process the output
def output_callback(word, final_state):
    model = word.split()[0]
    global_stack.append((model, final_state))

def save_report(title, data):
    with open(title + "-report.txt", 'w') as f:
        f.write("\n".join(map(str, data)))

if __name__ == '__main__':
    num_args = len(sys.argv)
    if (num_args < 2):
        print("ERRO: Arquivos de entrada não informados.\nExemplo: python3 main.py automato.txt palavras.txt")
        exit(1)
    elif (num_args < 3):
        print("ERRO: Arquivo com banco de palavras não informado.\nExemplo: python3 main.py automato.txt palavras.txt")
        exit(1)
    # last param is the tape file
    tape_file = sys.argv[num_args - 1]
    automaton_files = sys.argv[1:num_args - 1]
    automata = []
    paths = []
    words = []
    global_state = []
    output_name = "montadora-" + tape_file.split(".")[0]
    for filename in automaton_files:
        filename_parts = filename.split(".")[0].split(os.sep)
        automaton_name = filename_parts[len(filename_parts) - 1]
        with open(filename) as input_model:
            model = input_model.readlines()
            automaton = init_automaton(model)
            with open(tape_file) as input_samples:
                samples = input_samples.readlines()
                print(words)
                for sample in samples:
                    word = get_word(sample)
                    if word is None:
                        continue
                    print("------------------")
                    print("Validando: ", word)
                    result, path = validate(automaton, word, output_callback)
                    print("Resultado:", "VÁLIDO" if result == VALID else "INVÁLIDO")
                    if result == VALID:
                        automata.append(automaton)
                        paths.append(path)
                        words.append(word)
                        global_state.append(global_stack.copy())
    save_report(output_name, global_stack)
    save_multiple(automata, words, paths, global_state, output_name)
    print(f"Gerado: {output_name}.html")