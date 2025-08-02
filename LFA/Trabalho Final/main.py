############################################################
#### Authors: GIANLUCA RIGHETTO
####          JOAO PEDRO GARCIA GUEDES
#### PDA (pushdown automata) Simulator
#### This project is an exercise implementation to process
#### pushdown-down automata, that is, automata with
#### stack-based memory. It works with deterministic- (DFA),
#### non-deterministic- (NFA) and epsilon- (NFe) automata.
############################################################

from serialization import save
from constants import *
from automaton import *
import sys
import os


if __name__ == '__main__':
    num_args = len(sys.argv)
    if (num_args < 2):
        print("ERRO: Arquivos de entrada não informados.\nExemplo: python3 main.py automato.txt palavras.txt")
        exit(1)
    elif (num_args < 3):
        print("ERRO: Arquivo com banco de palavras não informado.\nExemplo: python3 main.py automato.txt palavras.txt")
        exit(1)
    filename = sys.argv[1]
    words = sys.argv[2]
    filename_parts = filename.split(".")[0].split(os.sep)
    automata_name = filename_parts[len(filename_parts) - 1]
    with open(filename) as input_model:
        model = input_model.readlines()
        automata = init_automaton(model)
        with open(words) as input_samples:
            samples = input_samples.readlines()
            for sample in samples:
                word = sample.strip()
                title = automata_name + "_" + word
                print("------------------")
                print("Validando: ", word)
                result, path = validate(automata, word)
                print("Resultado:", "VÁLIDO" if result == VALID else "INVÁLIDO")
                if result == VALID:
                    save(automata, word, path, title)
                    print(f"Gerado: {title}.html")

