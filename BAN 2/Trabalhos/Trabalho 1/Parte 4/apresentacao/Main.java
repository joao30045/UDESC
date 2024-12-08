package apresentacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import dados.*;
import negocio.Sistema;
import persistencia.ProfessorDAO;

public class Main {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws SQLException {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        int op = 0;

        do {
            op = menu(); 
            switch (op) {
                case 1:
                    menuProfessor(sistema, scanner);
                    break;
                case 2:
                    menuDepartamento(sistema, scanner);
                    break;
                case 3:
                    menuEstudante(sistema, scanner);
                    break;
                case 4:
                    menuParticipacao(sistema, scanner);
                    break;
                case 5:
                    menuProjeto(sistema, scanner);
                    break;
                case 6:
                    menuTrabalha(sistema, scanner);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0); 
    }
    public static int menu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Professor");
        System.out.println("2. Departamento");
        System.out.println("3. Estudante");
        System.out.println("4. Participacao");
        System.out.println("5. Projeto");
        System.out.println("6. Trabalha");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();
    }
    public static int opcoesProfessor(){
        System.out.println("\n--- Menu Professor ---");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Deletar");
        System.out.println("4. Consultar");
        System.out.println("5. Calcular tempo total de trabalho");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();   
    }   
    public static void menuProfessor(Sistema sistema, Scanner scanner){
        int op = 0;

        do{
            op = opcoesProfessor();
            switch(op){
                case 1:
                    inserirProfessor(sistema, scanner);
                    break;
                case 2:
                    atualizarProfessor(sistema, scanner);
                    break;
                case 3:
                    deletarProfessor(sistema, scanner);
                    break;
                case 4:
                    consultarProfessores(sistema);
                    break;
                case 5:
                    calcularTempoTotalProfessor(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do menu Professor.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }
    private static void inserirProfessor(Sistema sistema, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Sala: ");
        String sala = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        Professor professor = new Professor(0, nome, idade, sala, especialidade);
        try {
            sistema.inserirProfessor(professor);
            System.out.println("Professor inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void atualizarProfessor(Sistema sistema, Scanner scanner) {
        System.out.print("ID do Professor a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Nova sala: ");
        String sala = scanner.nextLine();
        System.out.print("Nova especialidade: ");
        String especialidade = scanner.nextLine();

        Professor professor = new Professor(id, nome, idade, sala, especialidade);
        try {
            sistema.atualizarProfessor(professor);
            System.out.println("Professor atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void deletarProfessor(Sistema sistema, Scanner scanner) {
        System.out.print("ID do Professor a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Professor professor = new Professor(id, null, 0, null, null); 
        try {
            sistema.deletarProfessor(professor);
            System.out.println("Professor deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void consultarProfessores(Sistema sistema) {
        try {
            List<Professor> professores = sistema.consultarProfessores();
            if (professores.isEmpty()) {
                System.out.println("Nenhum professor encontrado.");
            } else {
                System.out.println("Professores cadastrados:");
                for (Professor professor : professores) {
                    System.out.println("ID: " + professor.getId_professor() + 
                                       ", Nome: " + professor.getNome() + 
                                       ", Idade: " + professor.getIdade() + 
                                       ", Sala: " + professor.getSala() + 
                                       ", Especialidade: " + professor.getEspecialidade());
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void calcularTempoTotalProfessor(Scanner scanner){
        System.out.print("Digite o ID do professor: ");
        int professorID = scanner.nextInt();
        try{
            ProfessorDAO professorDAO = ProfessorDAO.getInstance();
            float tempoTotal = professorDAO.calcularTempoTotal(professorID);
            System.out.println("Tempo total de trabalho: " + tempoTotal + " horas");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public static int opcoesDepartamento(){
        System.out.println("\n--- Menu Departamento ---");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Deletar");
        System.out.println("4. Consultar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();   
    }   
    public static void menuDepartamento(Sistema sistema, Scanner scanner){
        int op = 0;

        do{
            op = opcoesDepartamento();
            switch(op){
                case 1:
                    inserirDepartamento(sistema, scanner);
                    break;
                case 2:
                    atualizarDepartamento(sistema, scanner);
                    break;
                case 3:
                    deletarDepartamento(sistema, scanner);
                    break;
                case 4:
                    consultarDepartamentos(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do menu Departamento.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }
    private static void inserirDepartamento(Sistema sistema, Scanner scanner){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Escritorio principal: ");
        String escritorio_principal = scanner.nextLine();
        System.out.println("Lider: ");
        int lider = scanner.nextInt();
        scanner.nextLine();

        Departamento departamento = new Departamento(0, nome, escritorio_principal, lider);
        try{
            sistema.inserirDepartamento(departamento);
            System.out.println("Departamento inserido com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    private static void atualizarDepartamento(Sistema sistema, Scanner scanner){
        System.out.println("ID do Departamento a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.println("Novo escritorio principal: ");
        String escritorio_principal = scanner.nextLine();
        System.out.println("Lider: ");
        int lider = scanner.nextInt();
        scanner.nextLine();

        Departamento departamento = new Departamento(id, nome, escritorio_principal, lider);
        try{
            sistema.atualizarDepartamento(departamento);
            System.out.println("Departamento atualizado com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    private static void deletarDepartamento(Sistema sistema, Scanner scanner){
        System.out.println("ID do Departamento a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Departamento departamento = new Departamento(id, null, null, 0);
        try {
            sistema.deletarDepartamento(departamento);
            System.out.println("Departamento deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void consultarDepartamentos(Sistema sistema){
        try{
            List<Departamento> departamentos = sistema.consultarDepartamentos();
            if (departamentos.isEmpty()){
                System.out.println("Nenhum departamento encontrado.");
            } else {
                System.out.println("Departamentos cadastrados:");
                for(Departamento departamento : departamentos){
                    System.out.println("ID: " + departamento.getId_departamento() +
                    ", Nome: " + departamento.getNome() + 
                    ", Escritorio principal: " + departamento.getEscritorio_principal() + 
                    ", Lider: " + departamento.getLider());
                }
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public static int opcoesEstudante(){
        System.out.println("\n--- Menu Estudante ---");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Deletar");
        System.out.println("4. Consultar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();   
    }   
    public static void menuEstudante(Sistema sistema, Scanner scanner){
        int op = 0;

        do{
            op = opcoesEstudante();
            switch(op){
                case 1:
                    inserirEstudante(sistema, scanner);
                    break;
                case 2:
                    atualizarEstudante(sistema, scanner);
                    break;
                case 3:
                    deletarEstudante(sistema, scanner);
                    break;
                case 4:
                    consultarEstudantes(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do menu Estudante.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }
    private static void inserirEstudante(Sistema sistema, Scanner scanner){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Tipo do curso: ");
        String tipo_do_curso = scanner.nextLine();
        System.out.println("Departamento: ");
        int depart = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Supervisor: ");
        int supervisor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Aconselhador: ");
        int aconselhador = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = new Estudante(0, nome, idade, tipo_do_curso, depart, supervisor, aconselhador);
        try{
            sistema.inserirEstudante(estudante);
            System.out.println("Estudante inserido com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private static void atualizarEstudante(Sistema sistema, Scanner scanner){
        System.out.println("ID do Estudante a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Tipo do curso: ");
        String tipo_do_curso = scanner.nextLine();
        System.out.println("Departamento: ");
        int depart = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Supervisor: ");
        int supervisor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Aconselhador: ");
        int aconselhador = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = new Estudante(id, nome, idade, tipo_do_curso, depart, supervisor, aconselhador);
        try{
            sistema.atualizarEstudante(estudante);
            System.out.println("Estudante atualizado com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    private static void deletarEstudante(Sistema sistema, Scanner scanner){
        System.out.println("ID do Estudante a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = new Estudante(id, null, 0, null, 0, 0, 0);
        try{
            sistema.deletarEstudante(estudante);
            System.out.println("Estudante deletado com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    private static void consultarEstudantes(Sistema sistema){
        try{
            List<Estudante> estudantes = sistema.consultarEstudantes();
            if(estudantes.isEmpty()){
                System.out.println("Nenhum estudante encontrado.");
            } else {
                System.out.println("Estudantes cadastrados:");
                for(Estudante estudante : estudantes){
                    System.out.println("ID: " + estudante.getId_estudante() +
                                        ", Nome: " + estudante.getNome() +
                                        ", Idade: " + estudante.getIdade() + 
                                        ", Tipo do curso: " + estudante.getTipo_do_curso() + 
                                        ", Departamento: " + estudante.getDepart() + 
                                        ", Supervisor: " + estudante.getSupervisor() + 
                                        ", Aconselhador: " + estudante.getAconselhador());
                }
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public static int opcoesParticipacao() {
        System.out.println("\n--- Menu Participacao ---");
        System.out.println("1. Inserir");
        System.out.println("2. Deletar");
        System.out.println("3. Consultar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();
    }

    public static void menuParticipacao(Sistema sistema, Scanner scanner) {
        int op = 0;

        do {
            op = opcoesParticipacao();
            switch (op) {
                case 1:
                    inserirParticipacao(sistema, scanner);
                    break;
                case 2:
                    deletarParticipacao(sistema, scanner);
                    break;
                case 3:
                    consultarParticipacao(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do menu Participacao.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }

    private static void inserirParticipacao(Sistema sistema, Scanner scanner) {
        System.out.println("id do estudante(assistente investigacao): ");
        int assistente_investigacao = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Numero do projeto: ");
        int numero_projeto = scanner.nextInt();
        scanner.nextLine();

        Participacao participacao = new Participacao(assistente_investigacao, numero_projeto);
        try {
            sistema.inserirParticipacao(participacao);
            System.out.println("Participacao inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deletarParticipacao(Sistema sistema, Scanner scanner) {
        System.out.print("ID do Estudante a deletar a participacao: ");
        int assistente_investigacao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Numero do Projeto a deletar a participacao: ");
        int numero_projeto = scanner.nextInt();
        scanner.nextLine();

        Participacao participacao = new Participacao(assistente_investigacao, numero_projeto);
        try {
            sistema.deletarParticipacao(participacao);
            System.out.println("Participacao deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void consultarParticipacao(Sistema sistema) {
        try {
            List<Participacao> participacoes = sistema.consultarParticipacao();
            if (participacoes.isEmpty()) {
                System.out.println("Nenhuma participacao encontrada.");
            } else {
                System.out.println("Participacoes cadastradas:");
                for (Participacao participacao : participacoes) {
                    System.out.println("Id do estudante: " + participacao.getAssistentes_investigacao() +
                            ", Numero do projeto: " + participacao.getNumero_projeto());
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int opcoesProjeto() {
        System.out.println("\n--- Menu Projeto ---");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Deletar");
        System.out.println("4. Consultar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();
    }

    public static void menuProjeto(Sistema sistema, Scanner scanner) {
        int op = 0;

        do {
            op = opcoesProjeto();
            switch (op) {
                case 1:
                    inserirProjeto(sistema, scanner);
                    break;
                case 2:
                    atualizarProjeto(sistema, scanner);
                    break;
                case 3:
                    deletarProjeto(sistema, scanner);
                    break;
                case 4:
                    consultarProjeto(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do menu Projeto.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }

    private static void inserirProjeto(Sistema sistema, Scanner scanner) {
        System.out.println("Id do projeto: ");
        int id_projeto = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Orgao financiador: ");
        String orgao_financiador = scanner.nextLine();

        System.out.println("Data do inicio: no formato 'xx/yy/zzzz' ");
        String data_inicio = scanner.nextLine();

        System.out.println("Data do fim: no formato 'xx/yy/zzzz' ");
        String data_fim = scanner.nextLine();

        System.out.println("Orcamento do projeto: ");
        Float orcamento = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Id do pesquisador principal: ");
        int pesquisador_principal = scanner.nextInt();
        scanner.nextLine();

        Projeto projeto = new Projeto(id_projeto, orgao_financiador, data_inicio, data_fim, orcamento,
                pesquisador_principal);
        try {
            sistema.inserirProjeto(projeto);
            System.out.println("Projeto inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void atualizarProjeto(Sistema sistema, Scanner scanner) {
        System.out.println("Orgao financiador: ");
        String orgao_financiador = scanner.nextLine();
        System.out.println("Data do inicio: no formato 'zzzz-yy-xx' ");
        String data_inicio = scanner.nextLine();
        System.out.println("Data do fim: no formato 'zzzz-yy-xx' ");
        String data_fim = scanner.nextLine();
        System.out.println("Orcamento do projeto: ");
        Float orcamento = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Id do pesquisador principal: ");
        int pesquisador_principal = scanner.nextInt();
        scanner.nextLine();
        System.out.println("ID do projeto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Projeto projeto = new Projeto(id, orgao_financiador, data_inicio, data_fim, orcamento, pesquisador_principal);
        try {
            sistema.atualizarProjeto(projeto);
            System.out.println("Projeto atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deletarProjeto(Sistema sistema, Scanner scanner) {
        System.out.print("ID do projeto a ser deletado: ");
        int id_projeto = scanner.nextInt();

        Projeto projeto = new Projeto(id_projeto, null, null, null, 0, 0);
        try {
            sistema.deletarProjeto(projeto);
            System.out.println("Projeto deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void consultarProjeto(Sistema sistema) {
        try {
            List<Projeto> projetos = sistema.consultarProjeto();
            if (projetos.isEmpty()) {
                System.out.println("Nenhum Projeto encontrada.");
            } else {
                System.out.println("Projetos cadastrados:");
                for (Projeto projeto : projetos) {
                    System.out.println("Id do projeto: " + projeto.getId_projeto() +
                            ", orgao financiador: " + projeto.getOrgao_financiador() +
                            ", data de inicio: " + projeto.getData_inicio() +
                            ", data do fim: " + projeto.getData_fim() +
                            ", orcamento: " + projeto.getOrcamento() +
                            ", pesquisador principal: " + projeto.getPesquisador_principal());

                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int opcoesTrabalha() {
        System.out.println("\n--- Menu Trabalha ---");
        System.out.println("1. Inserir");
        System.out.println("2. Deletar");
        System.out.println("3. Consultar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return input.nextInt();
    }

    public static void menuTrabalha(Sistema sistema, Scanner scanner) {
        int op = 0;

        do {
            op = opcoesTrabalha();
            switch (op) {
                case 1:
                    inserirTrabalha(sistema, scanner);
                    break;
                case 2:
                    deletarTrabalha(sistema, scanner);
                    break;
                case 3:
                    consultarTrabalha(sistema);
                    break;
                case 0:
                    System.out.println("Saindo do menu Trabalha.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (op != 0);
    }

    private static void inserirTrabalha(Sistema sistema, Scanner scanner) {
        System.out.println("Numero do departamento: ");
        int numero_departamento = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Professor que trabalha: ");
        int professor_trabalha = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Tempo de trabalho: ");
        Float tempo = scanner.nextFloat();
        scanner.nextLine();

        Trabalha trabalha = new Trabalha(numero_departamento, professor_trabalha, tempo);
        try {
            sistema.inserirTrabalha(trabalha);
            System.out.println("pessoa(Trabalha) inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deletarTrabalha(Sistema sistema, Scanner scanner) {
        System.out.print("numero do departamento do item(trabalha) que deseja excluir: ");
        int numero_departamento = scanner.nextInt();
        scanner.nextLine();
        System.out.println("id do Professor que do item(trabalha) que deseja excluir: ");
        int professor_trabalha = scanner.nextInt();
        scanner.nextLine();

        Trabalha trabalha = new Trabalha(numero_departamento, professor_trabalha, 0);
        try {
            sistema.deletarTrabalha(trabalha);
            System.out.println("Trabalha deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void consultarTrabalha(Sistema sistema) {
        try {
            List<Trabalha> trabalham = sistema.consultarTrabalha();
            if (trabalham.isEmpty()) {
                System.out.println("Nenhuma pessoa que trabalha encontrada.");
            } else {
                System.out.println("Pessoas que trabalham cadastrados:");
                for (Trabalha trabalha : trabalham) {
                    System.out.println("Numero do departamento: " + trabalha.getNumero_departamento() +
                            ", id do professor que trabalha: " + trabalha.getProfessor_trabalha() +
                            ", tempo de trabalho: " + trabalha.getTempo());

                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}