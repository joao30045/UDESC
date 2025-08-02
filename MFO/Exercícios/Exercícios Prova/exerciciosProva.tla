------------------- MODULE exerciciosProva ---------------------
EXTENDS FiniteSets, FiniteSetsExt, Integers, SequencesExt

\* As vezes eu recebia esse erro 
\* Error evaluating expression:
\* Was expecting "==== or more Module body"
\* Encountered "-" at line 2, column 18 and token "extension"
\* Parsing or semantic analysis failed.

\* 1 - Escreva um operador que recebe um conjunto de inteiros positivos e retorna o maior valor.
maiorValor(S) == FoldSet(LAMBDA i, acc: IF i > acc THEN i ELSE acc, 0, S)

testeMaiorValor == maiorValor({1,2,3,4})

\* 2 - Dado um conjunto de records como [ nome |-> "Gabriela", idade |-> 26 ], 
\* escreva um operador que recebe esse conjunto e retorna a diferença de idade 
\* entre o mais velho e o mais novo.
menorValor(S) == FoldSet(LAMBDA i, acc: IF i < acc THEN i ELSE acc, 200, S)

pessoas == {
  [nome |-> "Gabriela", idade |-> 26],
  [nome |-> "João", idade |-> 25],
  [nome |-> "Pedro", idade |-> 19]
}

idade(Pessoas) == LET idades == { p.idade : p \in Pessoas } IN maiorValor(idades) - menorValor(idades)

testeIdade == idade(pessoas)

\* 3 - Defina um valor que contenha todos os conjuntos possíveis com valores 
\* inteiros de 1 a 10, que contenham o número 5 ou o 6.
testeConjuntos == { S \in SUBSET {1,2,3,4,5,6,7,8,9,10} : 5 \in S \/ 6 \in S }

\* 4 - Escreva um operador que calcule o fatorial de um número. Lembre-se que 
\* recursão não é permitida.
fatorial(n) == FoldSet(LAMBDA i, acc: i * acc, 1, 1..n)

testeFatorial == fatorial(5)

\* 5 - Escreva um operador que recebe uma lista e retorna um mapa onde as chaves 
\* são os elementos da lista, e os valores são inteiros representando a quantidade 
\* de ocorrências daquele elemento na lista.

============================================================