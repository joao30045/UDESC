package projeto.dados;

public class Fornecedor {
    
    private int codFornecedor;
    private String nome;
    private String cnpj;
    private String email;

    public Fornecedor(int codFornecedor, String nome, String cnpj, String email) {
        this.codFornecedor = codFornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
    }
    public int getCodFornecedor() {
        return codFornecedor;
    }
    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String toString() {
        return "Codigo do fornecedor: " + codFornecedor + '\n' +
                "Nome: " + nome + '\n' +
                "CNPJ: " + cnpj + '\n' +
                "Email: " + email;
    }
}
