package apresentacao;

import java.util.Scanner;

import dados.Pessoa;
import negocio.Sistema;

public class Main {
    Scanner s = new Scanner(System.in);
    Sistema pessoas = new Sistema();

    public static void main(String[] args) {
        Main main = new Main();
        Pessoa p;
        do{
            p = main.criaPessoa();
            if(p != null){
                main.pessoas.addPessoa(p);
                main.mostraPessoas();
            }
        }while(p != null);
    }
    public Pessoa criaPessoa(){
        Pessoa p = new Pessoa();
        String aux;
        System.out.println("Digite o nome:");
        aux = s.nextLine();
        if(aux.equals("-1"))
            return null;
        p.setNome(aux);
        System.out.println("Digite o CPF:");
        aux = s.nextLine();
        if(aux.equals("-1"))
            return null;
        p.setCpf(aux);
        System.out.println("Digite a cidade:");
        aux = s.nextLine();
        if(aux.equals("-1"))
            return null;
        p.setCidade(aux);
        System.out.println("Digite a idade:");
        aux = s.nextLine();
        if(aux.equals("-1"))
            return null;
        p.setIdade(Integer.valueOf(aux));
        return p;
    }
    public void mostraPessoas(){
        System.out.println("Criancas:");
        for(Pessoa p : pessoas.getCriancas())
        System.out.println(p);
        System.out.println("Adolescentes:");
        for(Pessoa p : pessoas.getAdolescentes())
        System.out.println(p);
        System.out.println("Jovens:");
        for(Pessoa p : pessoas.getJovens())
		System.out.println(p);
		System.out.println("Adultos: ");
		for(Pessoa p : pessoas.getAdultos())
		System.out.println(p);
		System.out.println("Idosos: ");
		for(Pessoa p : pessoas.getIdosos())
		System.out.println(p);
    }
}
