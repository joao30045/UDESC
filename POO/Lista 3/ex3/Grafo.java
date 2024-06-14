package ex3;

public class Grafo extends Digrafo{
    public void adicionarAresta(int i, int j){
        super.ma.get(i).set(j, 1);
        super.ma.get(j).set(i, 1);
    }
}
