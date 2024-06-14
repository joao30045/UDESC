package projeto.dados;

import java.text.DecimalFormat;

public class MaisComprado {
    private String nome;
    private int quantidade;
    private float precounitcompra;

    public MaisComprado(String nome, int quantidade, float precounitcompra){
        this.nome = nome;
        this.quantidade = quantidade;
        this.precounitcompra = precounitcompra;
    }

    public String getNome() {
        return nome;
    }

    public float getPrecounitcompra() {
        return precounitcompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Nome produto: " + nome +
                "\nQuantidada: " + quantidade +
                "\nPreço unidade compra: R$" + df.format(precounitcompra) + 
                "\n";
    }
}
