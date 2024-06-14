package dados;

public class Ator {
    private int id;
    private String nome;
    private String dataNascimento;
    private String sexo;

    public Ator(int id, String n, String d, String s){
        this.id = id;
        this.nome = n;
        this.dataNascimento = d;
        this.sexo = s;
    }
    public Ator(){
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
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String toString(){
        return "Nome: " + nome + "Data de nascimento: " + dataNascimento + "Sexo: " + sexo;
    }
}
