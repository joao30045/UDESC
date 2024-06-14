package Main;

import Classes.*;

import java.util.Scanner;

public class Main {
    private static Sistema sis = new Sistema();
    private static Scanner in = new Scanner(System.in);

    private static void menu() {
        System.out.println("- - - - Menu - - - -");
        System.out.println("0  - Sair");
        System.out.println("1 - Barco");
        System.out.println("2  - Cais");
        System.out.println("3  - Companhia");
        System.out.println("4  - Estaleiro");
        System.out.println("5  - Funcionario");
        System.out.println("6  - Galpao");
        System.out.println("7  - Local");
        System.out.println("8  - Mover barco");
        System.out.println("9  - Carregar mercadoria");
        System.out.println("10 - Descarregar mercadoria");
        System.out.println("11 - Consertar barco");
        System.out.println("12 - Transportar mercadoria");
    }

    private static int menuBarco() {
        System.out.println("- - - - Menu Barco - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar barco");
        System.out.println("2 - Listar barcos");
        System.out.println("3 - Remover barco");
        return in.nextInt();
    }

    private static int menuCais() {
        System.out.println("- - - - Menu Cais - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar cais");
        System.out.println("2 - Listar cais");
        System.out.println("3 - Remover cais");
        return in.nextInt();
    }

    private static int menuCompanhia() {
        System.out.println("- - - - Menu Companhia - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar companhia");
        System.out.println("2 - Listar companhias");
        System.out.println("3 - Remover companhia");
        return in.nextInt();
    }

    private static int menuEstaleiro() {
        System.out.println("- - - - Menu Estaleiro - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar estaleiro");
        System.out.println("2 - Listar estaleiros");
        System.out.println("3 - Remover estaleiro");
        return in.nextInt();
    }

    private static int menuFuncionario() {
        System.out.println("- - - - Menu Funcionario - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Remover funcionario");
        return in.nextInt();
    }

    private static int menuGalpao() {
        System.out.println("- - - - Menu Galpao - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar galpao");
        System.out.println("2 - Listar galpoes");
        System.out.println("3 - Remover galpao");
        return in.nextInt();
    }

    private static int menuLocal() {
        System.out.println("- - - - Menu Local - - - -");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar local");
        System.out.println("2 - Listar local");
        System.out.println("3 - Remover local");
        return in.nextInt();
    }

    public static void main(String args[]) {
        int escolha = 1;
        try {
            while (escolha != 0) {

                menu();

                escolha = in.nextInt();
                in.nextLine();
                Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
                Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
                Local local = new Local( 1,"Galpao", "Nenhum");
                Local local2 = new Local(2, "Cais", "Nenhum");
                Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);
                barco.setQuebrado(false);
                Cais cais = new Cais(1, "Cais", "123", true, barco);
                Galpao galpao = new Galpao(1, "Galpao", "1234567", 1);
                switch(escolha) {
                    case 1:
                        opcaoBarco();
                        break;
                    case 2:
                        opcaoCais();
                        break;
                    case 3:
                        opcaoCompanhia();
                        break;
                    case 4:
                        opcaoEstaleiro();
                        break;
                    case 5:
                        opcaoFuncionario();
                        break;
                    case 6:
                        opcaoGalpao();
                        break;
                    case 7:
                        opcaoLocal();
                        break;
                    case 8:
                        sis.moverBarco(barco, local2);
                        break;
                    case 9:
                        sis.carregarMercadoria(barco, cais);
                        break;
                    case 10:
                        sis.descarregarMercadoria(barco, cais);
                        break;
                    case 11:
                        sis.consertarBarco(barco);
                        break;
                    case 12:
                        sis.transportarMercadoria(cais, galpao);
                        break;
                    default:
                        break;
                }
            }
        } catch(Exception e) {
            System.err.println("Erro");
        }
    }

    public static void opcaoBarco(){
        int escolha = 0;
        do {
            escolha = menuBarco();
            try {
                switch (escolha){
                    case 1:
                        sis.addBarco(0);
                        break;
                    case 2:
                        sis.listBarcos();
                        break;
                    case 3:
                        sis.alterBarco();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoCais(){
        int escolha = 0;
        do {
            escolha = menuCais();
            try {
                switch (escolha){
                    case 1:
                        sis.addCais(0);
                        break;
                    case 2:
                        sis.listCais();
                        break;
                    case 3:
                        sis.alterCais();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoCompanhia(){
        int escolha = 0;
        do {
            escolha = menuCompanhia();
            try {
                switch (escolha){
                    case 1:
                        sis.addCompanhia(0);
                        break;
                    case 2:
                        sis.listCompanhias();
                        break;
                    case 3:
                        sis.alterCompanhia();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoEstaleiro(){
        int escolha = 0;
        do {
            escolha = menuEstaleiro();
            try {
                switch (escolha){
                    case 1:
                        sis.addEstaleiro(0);
                        break;
                    case 2:
                        sis.listEstaleiros();
                        break;
                    case 3:
                        sis.alterEstaleiro();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoFuncionario(){
        int escolha = 0;
        do {
            escolha = menuFuncionario();
            try {
                switch (escolha){
                    case 1:
                        sis.addFuncionario(0);
                        break;
                    case 2:
                        sis.listFuncionarios();
                        break;
                    case 3:
                        sis.alterFuncionario();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoGalpao(){
        int escolha = 0;
        do {
            escolha = menuGalpao();
            try {
                switch (escolha){
                    case 1:
                        sis.addGalpao(0);
                        break;
                    case 2:
                        sis.listGalpoes();
                        break;
                    case 3:
                        sis.alterGalpao();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }

    public static void opcaoLocal(){
        int escolha = 0;
        do {
            escolha = menuLocal();
            try {
                switch (escolha){
                    case 1:
                        sis.addLocal(0);
                        break;
                    case 2:
                        sis.listLocais();
                        break;
                    case 3:
                        sis.alterLocal();
                        break;
                    default:
                        break;
                }
            }catch(Exception e) {
                System.err.println("Erro");
            }
        }while(escolha > 0 && escolha < 4);
    }
}