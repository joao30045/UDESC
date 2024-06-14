package ex4.apresentacao;

import ex4.dados.Pessoa;
import ex4.dados.Sorteador;

public class Main {
    public static void main(String[] args) {
        Sorteador s = new Sorteador();

        Pessoa p1 = new Pessoa();
        p1.setCpf("12345");
        p1.setNome("João");
        s.adicionaPessoa(p1);

        Pessoa p2 = new Pessoa();
        p2.setCpf("123456");
        p2.setNome("Pedro");
        s.adicionaPessoa(p2);

        Pessoa p3 = new Pessoa();
        p3.setCpf("1234567");
        p3.setNome("João Pedro");
        s.adicionaPessoa(p3);

        Pessoa escolhida = s.sortearProximo();
        System.out.println(escolhida);
    }
}
