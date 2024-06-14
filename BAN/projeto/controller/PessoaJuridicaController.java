package projeto.controller;

import java.util.Scanner;

import org.postgresql.core.SqlCommand;

import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;

import projeto.dados.PessoaJuridica;
import projeto.dados.PrincipaisPessoasJuridicas;
import projeto.models.PessoaJuridicaModel;
import projeto.controller.TrimestreController;

public class PessoaJuridicaController {
    public void createPessoaJuridica(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);

        System.out.println("Pessoa Jurídica");
        System.out.println("Insira os seguintes dados para criar um novo cliente:");

        System.out.print("Nome:\n> ");
        String nome = input.nextLine();

        System.out.print("Email:\n> ");
        String email = input.nextLine();

        System.out.print("Telefone:\n> ");
        String telefone = input.nextLine();

        System.out.print("Rua:\n> ");
        String rua = input.nextLine();

        System.out.print("Bairro\n> ");
        String bairro = input.nextLine();

        System.out.print("CEP:\n> ");
        int cep = Integer.parseInt(input.nextLine());

        System.out.print("CNPJ:\n> ");
        String cnpj = input.nextLine();

    
        PessoaJuridica p = new PessoaJuridica(0, 2, nome, email, telefone, rua, bairro, cep, cnpj);
        PessoaJuridicaModel.create(p, con);

        System.out.println("Cliente criado com sucesso!");
    }

    public static void listarPessoaJuridica(Connection con) throws SQLException{
        ArrayList all = PessoaJuridicaModel.listAll(con);

        System.out.println();
        System.out.println("Lista de Pessoas Jurídicas:");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma Pesso Jurídica encontrada");
            System.out.println();
        }else{
            Iterator<PessoaJuridica> it = all.iterator();

            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        }        
    }

    public static void listarPessoasJuridicasPrincipais(Connection con) throws SQLException{
        ArrayList all = PessoaJuridicaModel.listPessoasJuridicasPrincipais(con);

        System.out.println();
        System.out.println("Lista das princiapais Pessoas Jurídicas:");
        System.out.println("As Pessoas Jurídicas que mais compraram em quantidade e valor na loja.");
        System.out.println("A lista está ordenada pelo maior valor gasto");
        System.out.println();

        if(all.size() == 0){
            System.out.println("Nenhuma Pesso Jurídica encontrada");
            System.out.println();
        }else{
            Iterator<PrincipaisPessoasJuridicas> it = all.iterator();

            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }

            System.out.println();
        } 
    }

    public static void listarPessoasJuridicasCompraramMenosTrimestre(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Insira os seguintes dados: ");

        System.out.print("Ano:\n> ");
        int ano = input.nextInt();

        TrimestreController tri1 = new TrimestreController();
        int op = 0;
        do{
            op = tri1.menuTrimestre();
        }while (op == 0 || op > 4);
        int t = tri1.getTrimestre(op);

        ArrayList all = PessoaJuridicaModel.listPessoasJuridicasCompraramMenosTrimestre(t, ano, con);

        if(all.size() == 0){
            System.out.println("Nenhuma Pessoa Jurídica encontrada");
        }else{
            Iterator<PessoaJuridica> it = all.iterator();

            System.out.println();
            System.out.println(all.size() + " Pessoa(s) Jurídica(s) encontradas: ");

            while (it.hasNext()) {
                System.out.println(it.next().toString());
                System.out.println();
            }
        }
    }
}
