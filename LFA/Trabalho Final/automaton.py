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
from utils import *
from collections import deque

def walk(automata, stack, current_state, symbol, word:list, path:deque):
    if symbol == None and is_final(automata, current_state):
        return VALID, current_state
    transitions = automata.get(current_state, {}).get(SYMBOLS, {}).get(symbol, [])
    e_transitions = automata.get(current_state, {}).get(SYMBOLS, {}).get(EPSILON, [])
    question_transitions = automata.get(current_state, {}).get(SYMBOLS, {}).get(QUESTION_MARK, [])
    # only consider ?-transitions when the stack is empty
    if len(stack) == 0:
        for transition_tuple in question_transitions:
            next_state = transition_tuple[0]
            transition_result = apply_transition(transition_tuple, stack)
            if transition_result == CANNOT_APPLY_TRANSITION:
                continue
            result, final_state = walk(automata, stack.copy(), next_state, next(iter(word), None), word[1:], path)
            if result == VALID:
                path.appendleft((current_state, next_state, QUESTION_MARK, word, stack))
                return VALID, final_state
    # follow epsilon-transitions if they're available
    for transition_tuple in e_transitions:
        next_state = transition_tuple[0]
        transition_result = apply_transition(transition_tuple, stack)
        if transition_result == CANNOT_APPLY_TRANSITION:
            continue
        result, final_state = walk(automata, stack.copy(), next_state, symbol, word, path)
        if result == VALID:
            path.appendleft((current_state, next_state, EPSILON, [symbol] + word, stack))
            return VALID, final_state
    # follow normal transitions with symbols from the alphabet
    for transition_tuple in transitions:
        next_state = transition_tuple[0]
        transition_result = apply_transition(transition_tuple, stack)
        if transition_result == CANNOT_APPLY_TRANSITION:
            continue
        result, final_state = walk(automata, stack.copy(), next_state, next(iter(word), None), word[1:], path)
        if result == VALID:
            path.appendleft((current_state, next_state, symbol, word, stack))
            return VALID, final_state
    return INVALID, None

def validate(automata, input, output_callback = None):
    word = None
    if (" " in input):
        word = input.split()
    else:
        word = list(input)
    initial_state = get_initial_state(automata)
    stack = []
    path = deque()
    result, final_state = walk(automata, stack, initial_state, word[0], word[1:], path)
    # if result is VALID, apply OUTPUT function
    if result == VALID and output_callback is not None:
        output_callback(input, final_state)
    return result, path