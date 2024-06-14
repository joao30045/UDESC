package Classes;

public class Companhia {
    private int id;
    private String nome, email, fone, cnpj;
    private Funcionario responsavel;

    public Companhia(int id, String nome, String email, String fone, String cnpj, Funcionario responsavel) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
        this.cnpj = cnpj;
        this.responsavel = responsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public String toString() {
        String str = this.id + " - " + this.nome + ", Email:" + this.email + ", Fone:" + this.fone + ", CNPJ:" + this.cnpj;
        if (this.responsavel != null)
            str += ", Responsável: " + this.responsavel.getNome();
        return str;
    }
}