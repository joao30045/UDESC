package projeto.dados;

import java.text.DecimalFormat;

public class Produtos {
    
    private int codProduto;
    private float precoUnitVenda;
    private float precoUnitCompra;
    private String descricao;
    private int quantidade;
    private String datasheet;
    private String nome;

    public Produtos(int codProduto, float precoUnitVenda, float precoUnitCompra, String descricao, int quantidade,String nome, String datasheet) {
        this.codProduto = codProduto;
        this.precoUnitVenda = precoUnitVenda;
        this.precoUnitCompra = precoUnitCompra;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.nome = nome;
        this.datasheet = datasheet;
    }
    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    public float getPrecoUnitVenda() {
        return precoUnitVenda;
    }
    public void setPrecoUnitVenda(int precoUnitVenda) {
        this.precoUnitVenda = precoUnitVenda;
    }
    public float getPrecoUnitCompra() {
        return precoUnitCompra;
    }
    public void setPrecoUnitCompra(int precoUnitCompra) {
        this.precoUnitCompra = precoUnitCompra;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getDatasheet() {
        return datasheet;
    }
    public void setDatasheet(String datasheet) {
        this.datasheet = datasheet;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Codigo Produto: " + codProduto + '\n' + 
                "Nome: " + nome + '\n' +
                "Preco unitario de venda: R$" + df.format(precoUnitVenda) + '\n' +
                "Preco unitario de compra: $" + df.format(precoUnitCompra) + '\n' +
                "Descricao: " + descricao + '\n' +
                "Quantidade: " + quantidade + '\n' +
                "Datasheet: " + datasheet;
                
    } 
}