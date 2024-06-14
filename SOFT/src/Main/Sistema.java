package Main;

import Classes.*;
import Controle.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {
    private Scanner in = new Scanner(System.in);
    private BarcoContr barcos = new BarcoContr();
    private CompanhiaContr companhias = new CompanhiaContr();
    private FuncionarioContr funcionarios = new FuncionarioContr();
    private LocalContr locais = new LocalContr();
    private CaisContr cais = new CaisContr();
    private EstaleiroContr estaleiros = new EstaleiroContr();
    private GalpaoContr galpoes = new GalpaoContr();

    public Sistema() {
        funcionarios.add(new Funcionario(0, "Nenhum", "Nenhum", "Nenhum", "Nenhum", "Nenhum"));
        locais.add(new Local(0, "Nenhum", "0"));
        companhias.add(new Companhia(0, "Nenhuma", "Nenhum", "Nenhum", "Nenhum", (Funcionario)funcionarios.get(0)));
        barcos.add(new Barco(0, "Nenhum", "Nenhum", (Funcionario)funcionarios.get(0), (Companhia)companhias.get(0), (Local)locais.get(0)));
        galpoes.add(new Galpao(0, "Galpao de eletronicos", "123456789", 1));
        estaleiros.add(new Estaleiro(0, "Estaleiro", "123456", (Barco) barcos.get(0)));
        locais.add(new Local(1, "Local de Testes", "837465"));
        funcionarios.add(new Funcionario(1, "Lucas", "Testador", "teste@gmail.com", "9283456", "8374652"));
        companhias.add(new Companhia(1, "Companhia de Testes", "aksmhjkdfg@alk.com", "3984756", "9238465", (Funcionario)funcionarios.get(1)));
        barcos.add(new Barco(1, "Barco Testado", "Lancha", (Funcionario)funcionarios.get(1), (Companhia)companhias.get(1), (Local)locais.get(1)));
    }

    public int getLastId(ArrayList<Object> lista) {
        return (lista.size() - 1);
    }

    private void listAll(ArrayList<Object> lista) {
        Iterator<Object> it = lista.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    private Barco askBarco() {
        System.out.println("Escolha o Barco: ");
        listAll(barcos.getBarcos());
        try {
            int id = in.nextInt();
            in.nextLine();
            Barco barco = (Barco)barcos.get(id);
            return barco;
        } catch(Exception e) {
            System.err.println("Barco escolhido não existe");
        }
        return null;
    }

    private Companhia askCompanhia() {
        System.out.println("Escolha a Companhia: ");
        listAll(companhias.getCompanhias());
        try {
            int id = in.nextInt();
            in.nextLine();
            Companhia comp = (Companhia)companhias.get(id);
            return comp;
        } catch(Exception e) {
            System.err.println("Companhia escolhida não existe");
        }
        return null;
    }

    private Funcionario askFuncionario() {
        System.out.println("Escolha o Funcionário: ");
        listAll(funcionarios.getFuncionarios());
        try {
            int id = in.nextInt();
            in.nextLine();
            Funcionario func = (Funcionario)funcionarios.get(id);
            return func;
        } catch(Exception e) {
            System.err.println("Funcionário escolhido não existe");
        }
        return null;
    }

    private Local askLocal() {
        System.out.println("Escolha o Local: ");
        listAll(locais.getLocais());
        try {
            int id = in.nextInt();
            in.nextLine();
            Local local = (Local)locais.get(id);
            return local;
        } catch(Exception e) {
            System.err.println("Local escolhido não existe");
        }
        return null;
    }

    public void addBarco(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Tipo: ");
        String tipo = in.nextLine();
        Local local = askLocal();
        Companhia comp = askCompanhia();
        Funcionario func = askFuncionario();
        if (idd == 0)
            idd = getLastId(barcos.getBarcos()) + 1;
        barcos.add(new Barco(idd, nome, tipo, func, comp, local));
    }

    public void addCompanhia(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Fone: ");
        String fone = in.nextLine();
        System.out.println("CNPJ: ");
        String cnpj = in.nextLine();
        Funcionario resp = askFuncionario();
        if (idd == 0)
            idd = getLastId(companhias.getCompanhias()) + 1;

        companhias.add(new Companhia(idd, nome, email, fone, cnpj, resp));
    }

    public void addFuncionario(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Trabalho: ");
        String trabalho = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Fone: ");
        String fone = in.nextLine();
        System.out.println("CPF: ");
        String cpf = in.nextLine();
        if (idd == 0)
            idd = getLastId(funcionarios.getFuncionarios()) + 1;
        funcionarios.add(new Funcionario(idd, nome, trabalho, email, fone, cpf));
    }

    public void addLocal(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Coordenada: ");
        String coord = in.nextLine();
        if (idd == 0)
            idd = getLastId(locais.getLocais()) + 1;
        locais.add(new Local(idd, nome, coord));
    }

    public void listBarcos() {
        listAll(barcos.getBarcos());
    }

    public void listCompanhias() {
        listAll(companhias.getCompanhias());
    }

    public void listFuncionarios() {
        listAll(funcionarios.getFuncionarios());
    }

    public void listLocais() {
        listAll(locais.getLocais());
    }

    public void alterBarco() {
        Barco barco = askBarco();
        addBarco(barco.getId());
        barcos.remove(barco);
    }

    public void alterCompanhia() {
        Companhia comp = askCompanhia();
        addCompanhia(comp.getId());
        companhias.remove(comp);
    }

    public void alterFuncionario() {
        Funcionario func = askFuncionario();
        addFuncionario(func.getId());
        funcionarios.remove(func);
    }

    public void alterLocal() {
        Local local = askLocal();
        addLocal(local.getId());
        locais.remove(local);
    }

    public void listBarcosAtracados() {
        listAll(barcos.getBarcosAtracados());
    }

    public void listBarcosCCapitaes() {
        listAll(barcos.getBarcosCCapitaes());
    }

    public void addCapitaoBarco() {
        Barco barco = askBarco();
        Funcionario cap = askFuncionario();
        boolean isCap = barco.addCapitao(cap);
        if (isCap){
            System.out.println(cap.getNome() + " agora será o capitão do barco " + barco.getNome());
        }
    }

    public void listHistoricoCapitao() {
        Barco barco = askBarco();
        listAll(barco.getCapitaes());
    }


    public void listBarcosNoLocal() {
        Local local = askLocal();
        listAll(barcos.getBarcosNoLocal(local));
    }

    public void addCais(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Coordenada: ");
        String coord = in.nextLine();
        System.out.println("Tem mercadoria?");
        boolean temMercadoria = Boolean.parseBoolean(in.nextLine());
        Barco barcoAtracado = askBarco();
        if (idd == 0)
            idd = getLastId(cais.getCais()) + 1;
        cais.add(new Cais(idd, nome, coord, temMercadoria, barcoAtracado));
    }

    public void listCais() {
        listAll(cais.getCais());
    }

    private Cais askCais() {
        System.out.println("Escolha o cais: ");
        listAll(cais.getCais());
        try {
            int id = in.nextInt();
            in.nextLine();
            Cais c = (Cais)cais.get(id);
            return c;
        } catch(Exception e) {
            System.err.println("Cais escolhido não existe");
        }
        return null;
    }

    public void alterCais() {
        Cais cais = askCais();
        addCais(cais.getId());
        locais.remove(cais);
    }

    public void addEstaleiro(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Coordenada: ");
        String coord = in.nextLine();
        Barco barco = askBarco();
        if (idd == 0)
            idd = getLastId(estaleiros.getEstaleiros()) + 1;
        estaleiros.add(new Estaleiro(idd, nome, coord, barco));
    }

    public void listEstaleiros() {
        listAll(estaleiros.getEstaleiros());
    }

    private Estaleiro askEstaleiro() {
        System.out.println("Escolha o estaleiro: ");
        listAll(estaleiros.getEstaleiros());
        try {
            int id = in.nextInt();
            in.nextLine();
            Estaleiro estaleiro = (Estaleiro) estaleiros.get(id);
            return estaleiro;
        } catch(Exception e) {
            System.err.println("Estaleiro escolhido não existe");
        }
        return null;
    }

    public void alterEstaleiro() {
        Estaleiro estaleiro = askEstaleiro();
        addEstaleiro(estaleiro.getId());
        estaleiros.remove(estaleiro);
    }

    public void addGalpao(int idd) {
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Coordenada: ");
        String coord = in.nextLine();
        System.out.println("Tem mercadoria?");
        int qtdMercadoria = in.nextInt();
        in.nextLine();
        if (idd == 0)
            idd = getLastId(galpoes.getGalpoes()) + 1;
        galpoes.add(new Galpao(idd, nome, coord, qtdMercadoria));
    }

    public void listGalpoes() {
        listAll(galpoes.getGalpoes());
    }

    private Galpao askGalpao() {
        System.out.println("Escolha o galpao: ");
        listAll(galpoes.getGalpoes());
        try {
            int id = in.nextInt();
            in.nextLine();
            Galpao galpao = (Galpao) galpoes.get(id);
            return galpao;
        } catch(Exception e) {
            System.err.println("Galpao escolhido não existe");
        }
        return null;
    }

    public void alterGalpao() {
        Galpao galpao = askGalpao();
        addGalpao(galpao.getId());
        galpoes.remove(galpao);
    }

    public void moverBarco(Barco barco, Local local){
        System.out.println("O barco foi movido de " + barco.getLocal().getNome());
        if (barco.hasLocal()) {
            barco.setLocal(local);
        }
        System.out.println(" para o local " + barco.getLocal().getNome());
    }

    public void descarregarMercadoria(Barco barco, Cais cais){
        if(!cais.temMercadoria()){
            barco.setTemMercadoria(false);
            System.out.println("O barco foi descarregado.");
        }else{
            System.out.println("Nao foi possivel descarregar, o cais ja esta cheio.");
        }
    }

    public void carregarMercadoria(Barco barco, Cais cais){
        if(cais.temMercadoria()){
            barco.setTemMercadoria(true);
            System.out.println("O barco foi carregado.");
        }else{
            System.out.println("Nao ha mercadoria para ser carregada.");
        }
    }

    public void consertarBarco(Barco barco){
        if(barco.getLocal() instanceof Estaleiro){
            if(barco.isQuebrado()){
                barco.setQuebrado(false);
                System.out.println("O barco foi consertado");
            }else {
                System.out.println("O barco nao precisa de conserto.");
            }
        }else{
            System.out.println("O barco nao esta em um estaleiro para ser consertado.");
        }

    }

    public void transportarMercadoria(Cais cais, Galpao galpao){
        if(galpao.getQtdMercadoria() < 4){
            cais.setTemMercadoria(false);
            galpao.setQtdMercadoria(galpao.getQtdMercadoria() + 1);
            System.out.println("A mercadoria foi transportada de " + cais.getNome() + " para " + galpao.getNome());
            System.out.println("O " + galpao.getNome() + " esta com " + galpao.getQtdMercadoria() + " mercadoria(s).");
        }else{
            System.out.println("Galpao cheio");
        }
    }
}