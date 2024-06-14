package projeto.controller;

import projeto.dados.Kits;
import projeto.dados.KitsProdutos;
import projeto.dados.KitsQuantidade;
import projeto.models.KitsModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class KitsController {
    
    public void createKit(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar um novo kit:");

        System.out.print("Nome:\n>");
        String nome = input.nextLine();

        System.out.println("Produto:");
        int codProduto = ProdutosController.listarProdutosEnumerados(con);
        
        System.out.println("Produto que acompanha o Kit:");
        int codKitProduto = ProdutosController.listarProdutosEnumerados(con);

        System.out.print("Quantidade do produto:\n>");
        int quantidadeProduto = Integer.parseInt(input.nextLine());

        System.out.println();

        Kits k = new Kits(0, codKitProduto, codProduto, quantidadeProduto, nome);
        KitsModel.create(k, con);
        System.out.println("Kit criado com sucesso!!\n");
    }

    public void listarKits(Connection con) throws SQLException{
        ArrayList<Kits> all = KitsModel.listAll(con);

        System.out.println();
        System.out.println("Todos os Kits da loja:");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhum Kit encontrado");
            System.out.println();
        }else{
            Iterator<Kits> it = all.iterator();
            while(it.hasNext()){
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }

        
    }

    public static void listarKitsProdutos(Connection con) throws SQLException {
        ArrayList<KitsProdutos> all = KitsModel.listarKitsProdutos(con);

        System.out.println();
        System.out.println("Todos os kits e suas informacoes:");
        System.out.println();

        if(all.size() == 0) {
            System.out.println("Nenhum Kit encontrado");
            System.out.println();
        } else {
            Iterator<KitsProdutos> it = all.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void listarKitsQuantidades(Connection con) throws SQLException {
        HashSet<KitsQuantidade> all = KitsModel.listarKitsQuantidades(con);

        System.out.println();
        System.out.println("Quantidade total de produtos em kits:");
        System.out.println();

        if(all.size() == 0) {
            System.out.println("Nenhum Kit encontrado");
            System.out.println();
        } else {
            Iterator<KitsQuantidade> it = all.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void listarKitsQuantidadeDeProdutosComDatasheet(Connection con) throws SQLException{
        ArrayList<KitsQuantidade> all = KitsModel.listKitsQuantidadeDeProdutosComDatasheet(con);

        System.out.println();
        System.out.println("Lista da quantidade de Produtos com Datasheet por Kit");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhum Kit encontrado");
            System.out.println();
        }else{
            Iterator<KitsQuantidade> it = all.iterator();
            while (it.hasNext()) {
                KitsQuantidade k = (KitsQuantidade) it.next();

                System.out.println("Codigo Kit: " + k.getCodKit() + 
                                   "\nNome Kit: " + k.getNomeKit() +
                                   "\nQuantidade: " + k.getQuantidadeProduto());

                System.out.println();
            }

            System.out.println();
        }
    }
}