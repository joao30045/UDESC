package projeto.dados;

public class ProdutosQuantidade {
    private int codProduto;
    private String nome;
    private int quantidade;

    public ProdutosQuantidade(int codProduto, String nome, int quantidade){
        this.codProduto = codProduto;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Codigo do Produto: " + codProduto +
               "\nNome do Produto: " + nome + 
               "\nQuantidade: " + quantidade;
    }
}
