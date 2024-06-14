package projeto.controller;

import java.util.Scanner;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import projeto.dados.Venda;
import projeto.dados.Clientes;
import projeto.dados.FormaPagamento;
import projeto.models.VendaModel;
import projeto.models.ClientesModel;
import projeto.models.ProdutosModel;
import projeto.models.TransportadoraModel;

public class VendaController {
    public void createVenda(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);

        System.out.println();

        int codCliente = ClientesController.listarClientesEnumerados(con);
        System.out.println(codCliente);

        int codProduto = ProdutosController.listarProdutosEnumerados(con);
        System.out.println(codProduto);

        int codTransportadora = TransportadoraController.listarTransportadoraEnumera(con);
        System.out.println(codTransportadora);

        System.out.println("Quantidade\n>");
        int quantidade = input.nextInt();

        input.nextLine();
        System.out.println("Forma de pagamento\n>");
        String formaPagamento = input.nextLine();

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

        Venda v = new Venda(0, codCliente, codProduto, quantidade, formaPagamento, codTransportadora, data);
        VendaModel.create(v, con);

        System.out.println("Venda criada com sucesso");

    }

    public void printVenda(Venda v, Connection con) throws SQLException{
        System.out.println("Codigo Venda: " + v.getCodVenda());

        Clientes c = ClientesModel.getCliente(v.getCodCliente(), con);
        System.out.println("Cliente: " + c.getNome());
        System.out.println("Email: " + c.getEmail());

        System.out.println("Produto: " + ProdutosModel.getNomeProduto(v.getCodProduto(), con));
        System.out.println("Transportadora: " + TransportadoraModel.getNomeTransportadora(v.getCodTransportadora(), con));

        System.out.println("Quantidade: " + v.getQuantidade());
        System.out.println("Forma de pagamento: " + v.getFormaPagamento());

        System.out.println("Data: " + v.getData());
    }

    public void listarVendas(Connection con) throws SQLException{
        ArrayList all = VendaModel.listAll(con);

        System.out.println();
        System.out.println("Todas as Vendas realizadas pela loja:");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma Venda encontrada");
            System.out.println();
        }else{        
            Iterator<Venda> it = all.iterator();

             while (it.hasNext()) {
                printVenda(it.next(), con);
                System.out.println();
            }

            System.out.println();

        }
    }

    public void listarFormaPagamento(Connection con) throws SQLException{
        ArrayList all = VendaModel.listFormaPagamento(con);

        System.out.println();
        System.out.println("Formas de Pagamento:");
        System.out.println();

        Iterator<FormaPagamento> it = all.iterator();

        if(all.size() == 0){
            System.out.println("Nenhuma Forma de Pagamento encontrada");
            System.out.println();
        }else{
            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
             }

             System.out.println();
        }
    }

    public int menuTipoCliente(){
        System.out.println();
        System.out.println("Escolha o tipo de cliente: ");
        System.out.println("1 -- Pessoa Fisíca");
        System.out.println("2 -- Pessoa Jurídica");
        System.out.println("Sua opção: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public int tiposCliente(){
        int op = 0;

        do{
            op = menuTipoCliente();
        }while(op == 0 || op > 3);

        return op;
    }

    public void listarVendasClientesPorTipo(Connection con) throws SQLException{
        System.out.println();
        System.out.println("Listar as Vendas por tipo de Cliente");
        System.out.println();

        int tipo = tiposCliente();

        System.out.println();
        if(tipo == 1){
            System.out.println("Pessoas Fisícas: ");
        }else if(tipo == 2){
            System.out.println("Pessoas Jurídicas");
        }

        System.out.println();
        ArrayList all = VendaModel.listVendasClientesPorTipo(tipo, con);

        if(all.size() == 0){
            System.out.println("Nenhum Venda encontrado");
            System.out.println();
        }else{
            Iterator<Venda> it = all.iterator();

            while (it.hasNext()) {
                printVenda(it.next(), con);
                System.out.println();
            }
        } 
    }

    public void listarVendasComTransportadoraMaisBarata(Connection con) throws SQLException{
        System.out.println();
        System.out.println("Lista das Vendas que utilizaram a Transportadora com custo KM mais barato");
        System.out.println();

        System.out.println();
        ArrayList all = VendaModel.listVendasComTransportadoraMaisBarata(con);

        if(all.size() == 0){
            System.out.println("Nenhum Venda encontrado");
            System.out.println();
        }else{
            Iterator<Venda> it = all.iterator();

            while (it.hasNext()) {
                printVenda(it.next(), con);
                System.out.println();
            }
        } 
    }
}
