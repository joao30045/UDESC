package projeto.controller;

import projeto.dados.CompraFornecedor;
import projeto.dados.Fornecedor;
import projeto.dados.FornecedorQuantidade;
import projeto.models.FornecedorModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class FornecedorController {
    
    public void createFornecedor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar um novo fornecedor:");

        System.out.print("Nome:\n>");
        String nome = input.nextLine();

        System.out.print("CNPJ:\n>");
        String cnpj = input.nextLine();

        System.out.print("Email:\n>");
        String email = input.nextLine();

        Fornecedor fornecedor = new Fornecedor(0, nome, cnpj, email);
        FornecedorModel.create(fornecedor, con);
        System.out.println("Fornecedor criado com sucesso!!");
    }

    public void listarFornecedores(Connection con) throws SQLException {
        ArrayList<Fornecedor> all = FornecedorModel.listAll(con);
        Iterator<Fornecedor> it = all.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
            System.out.println();
        }
    }

    public static int listarFornecedoresEnumerados(Connection con) throws SQLException{
        int cont;
        int op = 0;

        ArrayList all = FornecedorModel.listAll(con);

        System.out.println("Fornecedor:");
        do{
            cont = 1;
            Iterator<Fornecedor> it = all.iterator();

            while (it.hasNext()) {
                System.out.print(cont + " -- ");
                System.out.println(it.next().getNome());
                cont++;
            }
            System.out.println("Sua opção: ");
            Scanner input = new Scanner(System.in);
            op = input.nextInt();
        }while(op == 0 && op > all.size());

        Fornecedor p = (Fornecedor) all.get(op - 1);
        return p.getCodFornecedor();
    }

    public static void listarComprasFornecedor(Connection con) throws SQLException {
        ArrayList<CompraFornecedor> all = FornecedorModel.listarComprasFornecedor(con);

        System.out.println();
        System.out.println("Todas as compras e seus fornecedores:");
        System.out.println();

        if(all.size() == 0) {
            System.out.println("Nenhuma compra encontrada");
            System.out.println();
        } else {
            Iterator<CompraFornecedor> it = all.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }
    }
    public static void listarFornecedorQuantidade(Connection con) throws SQLException {
        ArrayList<FornecedorQuantidade> all = FornecedorModel.listarFornecedorQuantidade(con);

        System.out.println();
        System.out.println("Os 3 principais fornecedores:");
        System.out.println();

        if(all.size() == 0) {
            System.out.println("Nenhuma fornecedor encontrado");
            System.out.println();
        } else {
            Iterator<FornecedorQuantidade> it = all.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }
    }
}
