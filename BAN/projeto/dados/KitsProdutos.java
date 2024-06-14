package projeto.dados;

public class KitsProdutos {
    
    private String nome;
    private String descricao;
    private String datasheet;
    private String nomeProduto;
    private int quantidade;


    public KitsProdutos(String nome, String descricao, String datasheet, String nomeProduto, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.datasheet = datasheet;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDatasheet() {
        return datasheet;
    }
    public void setDatasheet(String datasheet) {
        this.datasheet = datasheet;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String toString() {
        return "Nome: " + nome + 
                "\nQuantidade:" + quantidade +
                "\nNome produto: " + nomeProduto + 
                "\nDescricao: " + descricao +
                "\nDatasheet: " + datasheet;
                
                
    }
}
