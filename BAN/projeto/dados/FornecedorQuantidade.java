package projeto.dados;

public class FornecedorQuantidade {
        
    private String nomeFornecedor;
    private int totalQuantidade;

    public FornecedorQuantidade(String nomeFornecedor, int totalQuantidade) {
        this.nomeFornecedor = nomeFornecedor;
        this.totalQuantidade = totalQuantidade;
    }
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    public int getTotalQuantidade() {
        return totalQuantidade;
    }
    public void setTotalQuantidade(int totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }
    @Override
    public String toString() {
        return "Nome do fornecedor: " + nomeFornecedor +
                "\nQuantidade total: " + totalQuantidade; 
    }
}
