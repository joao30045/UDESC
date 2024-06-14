package projeto.dados;

import java.text.DecimalFormat;

public class ProdutosLucrativos {
    private String nome;
    private float lucro;

    public ProdutosLucrativos(String nome, float lucro){
        this.nome = nome;
        this.lucro = lucro;
    }

    public float getLucro() {
        return lucro;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Nome: " + nome +
               "\nLucro: R$" + df.format(lucro);
    }
}
