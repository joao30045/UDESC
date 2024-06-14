package projeto.dados;

import java.text.DecimalFormat;

public class PrincipaisPessoasJuridicas {
    private PessoaJuridica p;
    private int quantidade;
    private float valor;

    public PrincipaisPessoasJuridicas(PessoaJuridica p, int quantidade, float valor){
        this.p = p;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public PessoaJuridica getP() {
        return p;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return p.toString() +
                "\nQuantidade de produtos comprados: " + quantidade +
                "\nValor total gasto: R$" + df.format(valor);
    }
}
