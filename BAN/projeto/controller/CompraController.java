package projeto.controller;

import java.util.Scanner;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;

import projeto.dados.Compra;
import projeto.dados.MaisComprado;
import projeto.dados.ProdutosQuantidade;
import projeto.models.CompraModel;
import projeto.models.ProdutosModel;
import projeto.models.TransportadoraModel;
import projeto.models.FornecedorModel;

public class CompraController {
    public static void createCompra(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);

        System.out.println();

        int codProduto = ProdutosController.listarProdutosEnumerados(con);

        int codFornecedor = FornecedorController.listarFornecedoresEnumerados(con);

        int codTransportadora = TransportadoraController.listarTransportadoraEnumera(con);

        System.out.print("Quantidade\n>");
        int quantidade = input.nextInt();

        input.nextLine();

        System.out.println();
        System.out.println("Data da entrega:");
        System.out.print("Dia:\n> ");
        String dia = input.nextLine();
        System.out.println();
        System.out.print("Mês\n> ");
        String mes = input.nextLine();
        System.out.println();
        System.out.print("Ano:\n> ");
        String ano = input.nextLine();
        System.out.println();

        Date data = Date.valueOf(ano + '-' + mes + '-' + dia);

        Compra c = new Compra(0, codFornecedor, codProduto, quantidade, codTransportadora, data);
        CompraModel.create(c, con);

        System.out.println("Compra criada com sucesso!");
    }

    public static void printCompra(Compra c, Connection con) throws SQLException{
        System.out.println("Codigo Compra: " + c.getCodCompra());

        System.out.println("Produto: " + ProdutosModel.getNomeProduto(c.getCodProduto(), con));
        System.out.println("Fornecedor: " + FornecedorModel.getNomeFornecedor(c.getCodFornecedor(), con) );
        System.out.println("Transportadora: " + TransportadoraModel.getNomeTransportadora(c.getCodTransportadora(), con));

        System.out.println("Quantidade: " + c.getQuantidade());
    }

    public static void listarCompras(Connection con) throws SQLException{
        ArrayList all = CompraModel.listAll(con);

        System.out.println();
        System.out.println("Todas as Compras realizadas pela loja:");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma Compra encontrada");
            System.out.println();
        }else{
            Iterator<Compra> it = all.iterator();
             while(it.hasNext()){
                printCompra(it.next(), con);
                System.out.println();
            }

            System.out.println();
        }
    }

    public static void listarProdutosMaisComprados(Connection con) throws SQLException{
        ArrayList all = CompraModel.listProdutosMaisComprados(con);

        System.out.println();
        System.out.println("Lista dos produtos mais comprados:");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma compra encontrada");
            System.out.println();
        }else{
            Iterator<MaisComprado> it = all.iterator();

            while (it.hasNext()) {
             System.out.println(it.next().toString());
             System.out.println();
           }
           System.out.println();
        }

    }

    public static void QuantidadeProdutosCompradosComDatasheet(Connection con) throws SQLException{
        ArrayList all = CompraModel.listQuantidadeProdutosCompradosComDatasheet(con);

        System.out.println();
        System.out.println("Lista dos produtos comprados que possuem Datasheet");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhum Produto encontrado");
            System.out.println();
        }else{
            Iterator<ProdutosQuantidade> it = all.iterator();

            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }
            System.out.println();
        }
    }
}
