package projeto.dados;

public class KitsQuantidade {

    private int codKit;
    private String nomeKit;
    private int quantidadeProduto;
    private int totalProdutosEmKits;
    
    public KitsQuantidade(int codKit, String nomeKit, int quantidadeProduto, int totalProdutosEmKits) {
        this.codKit = codKit;
        this.nomeKit = nomeKit;
        this.quantidadeProduto = quantidadeProduto;
        this.totalProdutosEmKits = totalProdutosEmKits;
    }

    public KitsQuantidade(int codKit, String nomeKit, int quantidade){
        this.codKit = codKit;
        this.nomeKit = nomeKit;
        this.quantidadeProduto = quantidade;
    }

    public int getCodKit() {
        return codKit;
    }

    public void setCodKit(int codKit) {
        this.codKit = codKit;
    }

    public String getNomeKit() {
        return nomeKit;
    }

    public void setNomeKit(String nomeKit) {
        this.nomeKit = nomeKit;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getTotalProdutosEmKits() {
        return totalProdutosEmKits;
    }

    public void setTotalProdutosEmKits(int totalProdutosEmKits) {
        this.totalProdutosEmKits = totalProdutosEmKits;
    }

    public String toString() {
        return "Codigo kit: " + codKit + 
                "\nNome kit: " + nomeKit + 
                "\nQuantidade: " + quantidadeProduto + 
                "\nTotal de produtos em kits: " + totalProdutosEmKits;
    }
}
