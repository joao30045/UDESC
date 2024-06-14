package ex3;

public class Main {
    public static void main(String[] args) {
        Digrafo d = new Digrafo();
        Grafo g = new Grafo();

        d.adicionarVertice();
        d.adicionarVertice();
        d.adicionarVertice();
        d.adicionarVertice();
        d.adicionarVertice();
        d.adicionarVertice();
        d.adicionarAresta(0,1);
        d.adicionarAresta(1,2);
        d.adicionarAresta(2,3);
        System.out.println("Digrafo");
        System.out.println(d.toString());

        g.adicionarVertice();
        g.adicionarVertice();
        g.adicionarVertice();
        g.adicionarVertice();
        g.adicionarVertice();
        g.adicionarVertice();
        g.adicionarAresta(0,1);
        g.adicionarAresta(1,2);
        g.adicionarAresta(2,3);
        System.out.println("Grafo");
        System.out.println(g.toString()); 

    }
}
