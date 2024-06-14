package dados;

public class Usuario{
    private int id;
    private String dataNascimento;
    private String nome;
    private String senha;

    public Usuario(int id, String n, String d, String s){
        this.id = id;
        this.nome = n;
        this.dataNascimento = d;
        this.senha = s;
    }
    public Usuario(){
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}