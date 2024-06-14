package projeto.dados;

public class Kits {
    
    private int codKit;
    private int codKitProduto;
    private int codProduto;
    private int quantidadeProduto;
    private String nome;

    public Kits(int codKit, int codKitProduto, int codProduto, int quantidadeProduto, String nome) {
        this.codKit = codKit;
        this.codKitProduto = codKitProduto;
        this.codProduto = codProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.nome = nome;
    }
    public int getCodKit() {
        return codKit;
    }
    public void setCodKit(int codKit) {
        this.codKit = codKit;
    }
    public int getCodKitProduto() {
        return codKitProduto;
    }
    public void setCodKitProduto(int codKitProduto) {
        this.codKitProduto = codKitProduto;
    }
    public int getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }
    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String toString() {
        return  "Codigo do Kit: " + codKit + '\n' +
                "Codigo KitProduto: " + codKitProduto + '\n' +
                "Codigo do Produto: " + codProduto + '\n' +
                "Quantidade do produto: " + quantidadeProduto + '\n' +
                "Nome do Kit: " + nome;
    }
}
