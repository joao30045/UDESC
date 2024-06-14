package projeto.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import projeto.controller.PessoaFisicaController;
import projeto.controller.PessoaJuridicaController;
import projeto.controller.TransportadoraController;
import projeto.controller.ProdutosController;
import projeto.controller.FornecedorController;
import projeto.controller.KitsController;
import projeto.controller.VendaController;
import projeto.controller.CompraController;
import projeto.models.Conexao;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException{
        
        Conexao c = new Conexao();
        Connection con = c.getConnection();

        int op = 0;

        do{
            op = menu();
                switch (op) {
                    case 1:
                       menuCliente(con);
                       break;
                    case 2:
                        menuProduto(con);
                        break;
                    case 3:
                        menuKits(con);
                        break;
                    case 4:
                        menuVenda(con);
                        break;
                    case 5:
                        menuCompra(con);
                        break;
                    case 6:
                        menuFornecedor(con);
                        break;
                    case 7:
                        menuTransportadora(con);
                        break;
                  
                    default:
                        break;
                }
        }while(op>0 && op < 8);
        con.close();
    }

    public static int menu(){
        System.out.println("\nMenu:");
        System.out.println("Informe o número da opção que deseja executar:");
        System.out.println("1 -- Clientes");
        System.out.println("2 -- Produtos");
        System.err.println("3 -- Kits");
        System.out.println("4 -- Venda");
        System.out.println("5 -- Compra");
        System.out.println("6 -- Fornecedor");
        System.out.println("7 -- Transportadora");
        System.out.println("8 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static int opcoesCliente(){
        System.out.println();
        System.out.println("Clientes: ");
        System.out.println("Informe o número da opção que deseja executar: ");

        System.out.println("\nPessoa Física");
        System.out.println("1 -- Inserir um nova Pessoa Física");
        System.out.println("2 -- Listar todas as Pessoas Físicas");
        System.out.println("3 -- Listar todas as Pessoas Físicas que compraram em um determinado Trimestre");
        System.out.println("4 -- Número de Pessoas Físicas que compraram todos os produtos");

        System.out.println("\nPessoa Jurídica");
        System.out.println("5 -- Inserir um nova Pessoa Jurídica");
        System.out.println("6 -- Listar todas as Pessoas Jurídicas");
        System.out.println("7 -- Listar principais Pessoas Jurídicas");
        System.out.println("8 -- Listar Pessoas Jurídicas que compraram menos produtos do que no Trimestre anterior");

        System.out.println("\n9 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public  static void menuCliente(Connection con){
        int op = 0;

        do{
            op = opcoesCliente();

            try{
                switch (op) {
                    case 1:
                        new PessoaFisicaController().createPessoFisica(con);
                        break;
                    case 2:
                        new PessoaFisicaController().listarPessoaFisica(con);
                        break;
                    case 3:
                        new PessoaFisicaController().listarPessoaFisicaComprouTrimestre(con);
                        break;
                    case 4:
                        new PessoaFisicaController().numPessoasFisicasCompraramTodosOsProdutos(con);
                        break;
                    case 5:
                        new PessoaJuridicaController().createPessoaJuridica(con);
                        break;
                    case 6:
                        new PessoaJuridicaController().listarPessoaJuridica(con);
                        break;
                    case 7:
                        new PessoaJuridicaController().listarPessoasJuridicasPrincipais(con);
                        break;
                    case 8:
                        new PessoaJuridicaController().listarPessoasJuridicasCompraramMenosTrimestre(con);
                        break;
                    default:
                        break;
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op < 9);
    }

    public static int opcoesProduto(){
        System.out.println("\nProduto: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Produto");
        System.out.println("2 -- Listar todas os Produtos");
        System.out.println("3 -- Listar os 10 Produtos mais lucrativos");
        System.out.println("4 -- Listar os Produtos com Preço por Unidade de Venda acima da média");
        System.out.println("5 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuProduto(Connection con){
        int op = 0;

        do{
            op = opcoesProduto();

            try{
                switch (op) {
                    case 1:
                        new ProdutosController().createProduto(con);
                        break;
                    case 2:
                        new ProdutosController().listarProdutos(con);
                        break;
                    case 3:
                        new ProdutosController().listar10ProdutosMaisLucrativos(con);
                        break;
                    case 4:
                        new ProdutosController().listarProdutosAcimaDaMedia(con);
                        break;
                
                    default:
                        break;
                }
            }catch(SQLException ex){

            }
        }while(op> 0 && op<5);
    }

    public static int opcoesVenda(){
        System.out.println("\nVenda: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Vendas");
        System.out.println("2 -- Listar todas as Vendas");
        System.out.println("3 -- Listar as Formas de Pagamentos");
        System.out.println("4 -- Listar as Vendas por tipo de Cliente");
        System.out.println("5 -- Listar as Vendas que utilizaram a Transportadora com custo KM mais barato");
        System.out.println("6 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuVenda(Connection con){
        int op = 0;

        do{
            op = opcoesVenda();

            try{
                switch (op) {
                    case 1:
                        new VendaController().createVenda(con);
                        break;
                    case 2:
                        new VendaController().listarVendas(con);
                        break;
                    case 3:
                        new VendaController().listarFormaPagamento(con);
                        break;
                    case 4:
                        new VendaController().listarVendasClientesPorTipo(con);
                        break;
                    case 5:
                        new VendaController().listarVendasComTransportadoraMaisBarata(con);
                        break;
                    default:
                        break;
                }
                
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op<6);
    }

    public static int opcoesCompra(){
        System.out.println("\nCompra: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Compra");
        System.out.println("2 -- Listar todas as Compras");
        System.out.println("3 -- Listar os Produtos mais Comprados");
        System.out.println("4 -- Listar os Produtos com Datasheet");
        System.out.println("5 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuCompra(Connection con){
        int op = 0;

        do{
            op = opcoesCompra();
            
            try{
                switch (op) {
                    case 1:
                        new CompraController().createCompra(con);
                        break;
                    case 2:
                        new CompraController().listarCompras(con);
                        break;
                    case 3:
                        new CompraController().listarProdutosMaisComprados(con);
                        break;
                    case 4:
                        new CompraController().QuantidadeProdutosCompradosComDatasheet(con);
                        break;
                    default:
                        break;
                }

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op<5);
    }

    public static int opcoesFornecedor(){
        System.out.println("\nFornecedor: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Fornecedor");
        System.out.println("2 -- Listar todos os Fornecedores");
        System.out.println("3 -- Listar as Compras e Fornecedores");
        System.out.println("4 -- Listar os 3 principais Fornecedores");
        System.out.println("5 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuFornecedor(Connection con){
        int op = 0;

        do{
            op = opcoesFornecedor();
            
            try{
                switch (op) {
                    case 1:
                        new FornecedorController().createFornecedor(con);
                        break;
                    case 2:
                        new FornecedorController().listarFornecedores(con);
                        break;
                    case 3:
                        new FornecedorController().listarComprasFornecedor(con);
                        break;
                    case 4:
                        new FornecedorController().listarFornecedorQuantidade(con);
                        break;
                    default:
                        break;
                }

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op<5);
    }

    public static int opcoesKits(){
        System.out.println("\nKits: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Kits");
        System.out.println("2 -- Listar todos os Kits");
        System.out.println("3 -- Listar os Kits e informacoes dos Produtos");
        System.out.println("4 -- Listar a quantidade de Produtos com Datasheet por Kit");
        System.out.println("5 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuKits(Connection con){
        int op = 0;

        do{
            op = opcoesKits();
            
            try{
                switch (op) {
                    case 1:
                        new KitsController().createKit(con);
                        break;
                    case 2:
                        new KitsController().listarKits(con);
                        break;
                    case 3:
                        new KitsController().listarKitsProdutos(con);
                        break;
                    case 4:
                        new KitsController().listarKitsQuantidadeDeProdutosComDatasheet(con);
                        break;
                    default:
                        break;
                }

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op<5);
    }

    public static int opcaoTransportadora(){
        System.out.println("\nTransportadora: ");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 -- Inserir Transportadora");
        System.out.println("2 -- Listar todas as Transportadoras");
        System.out.println("3 -- Listar número de vezes que as Transportadoras foram utilizadas nas Vendas");
        System.out.println("4 -- Quantidade de vezes que uma Transportadora foi utilizada na Venda de um Produto");
        System.out.println("6 -- Sair");
        System.out.println("Sua opção: ");
        return input.nextInt();
    }

    public static void menuTransportadora(Connection con){
        int op = 0;

        do{
            op = opcaoTransportadora();

            try{
                switch (op) {
                    case 1:
                        new TransportadoraController().createTransportadora(con);
                        break;
                    case 2:
                        new TransportadoraController().listarTransportadoras(con);
                        break;
                    case 3:
                        new TransportadoraController().listarTransportadoraQuantidade(con);
                        break;
                    case 4:
                        new TransportadoraController().listarTransportadorasUsadasParaProdutos(con);
                        break;
                    default:
                        break;
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                continue;
            }
        }while(op>0 && op<5);
    }
}

