package ex1;

public class Mod implements IOperacaoInteira{
    public int executar(int v1, int v2) {
        int p,q;
        q = v1 / v2;
        p = q * v2;
        return v1 - p;
    }
}
