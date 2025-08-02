module exercicios {
  // 1 - Escreva um operador que recebe um conjunto de inteiros positivos e 
  // retorna o maior valor.
  Set(1,2,3,4).fold(0, (acc, i) => if (acc > i) acc else i)
  // 2 - Dado um conjunto de records do tipo { nome: str, idade: int }, escreva 
  // um operador que recebe esse conjunto e retorna a diferença de idade entre o 
  // mais velho e o mais novo.
  pure def e2(r) = {
    pure val maior = r.fold(0, (acc, i) => if (acc > i.idade) acc else i.idade)
    pure val menor = r.fold(200, (acc, i) => if (acc < i.idade) acc else i.idade)
    maior - menor
  }
  e2(Set({nome: "G", idade: 26}, { nome: "Pedro", idade: 25 }, { nome: "Breno", idade: 19 }))
  // 3 - Defina um valor que contenha todos os conjuntos possíveis com valores 
  // inteiros de 1 a 10, com tamanho maior que 2 e menor que 5.
  val e3 = 1.to(10).powerset().filter(s => s.size > 2 and s.size < 5)
  // 4 - Escreva um operador que calcule o fatorial de um número. Lembre-se que 
  // recursão não é permitida.
  1.to(5).fold(1 , (acc, i) => acc * i)
  // 5 - Escreva um operador que recebe uma lista e retorna um mapa onde as 
  // chaves são os elementos da lista, e os valores são inteiros representando a 
  // quantidade de ocorrências daquele elemento na lista.
  [1,1,1,4,4].foldl(Map(), (acc, i) => if (acc.keys().contains(i)) acc.setBy(i, curr => curr + 1) else acc.put(i, 1))
}