package projeto.controller;

import java.util.Scanner;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;

import projeto.dados.Transportadora;
import projeto.dados.TransportadoraQuantidade;
import projeto.models.TransportadoraModel;


public class TransportadoraController {
    public void createTransportadora(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);

        System.out.println("Transportadora");
        System.out.println("Insira os seguintes dados para criar uma nova transportadora:");

        System.out.print("Nome:\n> ");
        String nome = input.nextLine();

        System.out.print("CNPJ:\n>");
        String cnpj = input.nextLine();

        System.out.print("Email:\n>");
        String email = input.nextLine();

        System.out.print("Custo por KM:\n>");
        float custokm = Float.parseFloat(input.nextLine());

        Transportadora t = new Transportadora(0, nome, cnpj, email, custokm);
        TransportadoraModel.create(t, con);

        System.out.println("Transportadora criada com sucesso!");
    }

    public static void listarTransportadoras(Connection con) throws SQLException{
        ArrayList all = TransportadoraModel.listAll(con);

        System.out.println();
        System.out.println("Lista de transportadoras: ");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma transportadora encontrada");
            System.out.println();
        }else{
            Iterator<Transportadora> it = all.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }
    }

    public static int listarTransportadoraEnumera(Connection con) throws SQLException{
        int cont;
        int op = 0;

        ArrayList all = TransportadoraModel.listAll(con);

        System.out.println("Transportadora:");
        do{
            cont = 1;
            Iterator<Transportadora> it = all.iterator();

            while (it.hasNext()) {
                System.out.print(cont + " -- ");
                System.out.println(it.next().getNome());
                cont++;
            }
            System.out.println("Sua opção: ");
            Scanner input = new Scanner(System.in);
            op = input.nextInt();
        }while(op == 0 || op > all.size());

        Transportadora t = (Transportadora) all.get(op - 1);
        return t.getCodTransportadora();
    }

    public static void listarTransportadoraQuantidade(Connection con) throws SQLException{
        ArrayList all = TransportadoraModel.listTransportadoraQuantidade(con);
        Iterator<TransportadoraQuantidade> it = all.iterator();

        System.out.println();
        System.out.println("Lista das transportadoras utilizadas nas vendas,\n e a quantidade de vezes que foram utilizadas: ");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma transportadora encontrada");
            System.out.println();
        }else{
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }
    }

    public static void transportadoraComMaiorPreco(Connection con) throws SQLException{
        Transportadora t = TransportadoraModel.getTransportadoraComMaiorPreco(con);

        System.out.println();
        System.out.println("Transportadora com o maior preço");
        System.out.println();

        System.out.println("Nome: " + t.getNome() +
                            "\nCusto Km: " + t.getCustoKM()
                            );
    }

    public static void listarTransportadorasUsadasParaProdutos(Connection con) throws SQLException{
        System.out.println("");
        System.out.println("Lista das Transportadoras que já foram usada na Venda de certo produto");
        System.out.println("");

        int codProduto = ProdutosController.listarProdutosEnumerados(con);

        ArrayList<TransportadoraQuantidade> all = TransportadoraModel.listTransportadorasUsadasParaProdutos(codProduto, con);

        if(all.size() == 0){
            System.out.println("Nenhuma Transportadora encontrada");
            System.out.println();
        }else{
            Iterator<TransportadoraQuantidade> it = all.iterator();

            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }


    }
}
