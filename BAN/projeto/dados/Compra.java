package projeto.dados;

import java.sql.Date;

public class Compra {
    private int codCompra;
    private int codFornecedor;
    private int codProduto;
    private int quantidade;
    private int codTransportadora;
    private Date data;

    public Compra(int codCompra, int codFornecedor, int codProduto, int quantidade, int codTransportadora, Date data){
        this.codCompra = codCompra;
        this.codFornecedor = codFornecedor;
        this.codProduto = codProduto;
        this.quantidade = quantidade;
        this.codTransportadora = codTransportadora;
        this.data = data;
    }

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodTransportadora() {
        return codTransportadora;
    }

    public void setCodTransportadora(int codTransportadora) {
        this.codTransportadora = codTransportadora;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Codigo Compra: " + codCompra +
                "\nCodigo Fornecedor: " + codFornecedor +
                 "\nCodigo Produto: " + codProduto +
                 "\nQuantidade: " + quantidade + 
                 "\nCodigo Transportadora: " + codTransportadora +
                 "\nData: " + data;
    }
}
