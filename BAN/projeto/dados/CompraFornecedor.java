package projeto.dados;

public class CompraFornecedor {
    
    private int codCompra;
    private int quantidade;
    private String nomeFornecedor;

    public CompraFornecedor(int codCompra, int quantidade, String nomeFornecedor){
        this.codCompra = codCompra;
        this.quantidade = quantidade;
        this.nomeFornecedor = nomeFornecedor;
    }
    public int getCodCompra() {
        return codCompra;
    }
    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    public String toString() {
        return "Codigo compra: " + codCompra + + '\n' +
                "\nQuantidade: " + quantidade + '\n' +
                "Nome fornecedor: " + nomeFornecedor;
    }
}
