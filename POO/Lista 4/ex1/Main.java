package ex1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        List<IOperacaoInteira> lista = new LinkedList<IOperacaoInteira>();
        Soma s = new Soma();
        Multiplicacao m = new Multiplicacao();
        Mod mod = new Mod();
        MDC mdc = new MDC();
        lista.add(s);
        lista.add(m);
        lista.add(mod);
        lista.add(mdc);
        int a = r.nextInt(50);
        int b = r.nextInt(50);
        System.out.println("Número 1: " + a);
        System.out.println("Numero 2: " + b);
        for(IOperacaoInteira o : lista)
            System.out.println(o.executar(a,b));
    }
}