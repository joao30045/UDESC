package Classes;

public class Funcionario {
    private int id;
    private String nome, trabalho, email, fone, cpf;

    public Funcionario(int id, String nome, String trabalho, String email, String fone, String cpf) {
        super();
        this.id = id;
        this.nome = nome;
        this.trabalho = trabalho;
        this.email = email;
        this.fone = fone;
        this.cpf = cpf;
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

    public String getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String toString() {
        return this.id + " - " + this.nome + ", Trabalho: " + this.trabalho + ", Email: " + this.email + ", Fone: " + this.getFone() + ", CPF: " + this.cpf;
    }
}