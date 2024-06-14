from dados.Carro import *
from dados.Cliente import *

carro1 = Carro()
carro2 = Carro()

cliente = Cliente()

cliente.nome = "João"
cliente.cpf = "123456"
cliente.endereco = "Rua"

carro1.modelo = "Polo"
carro1.fabricante = "VW"
carro1.cor = "Azul"

carro2.fabricante = "Ford"
carro2.modelo = "F-1000"
carro2.cor = "Cinza"

print("Carro 1: ")
print(carro1)
print("Carro 2:")
print(carro2)
print("Cliente:")
print(cliente)
