package dados;

public class Professor {
    private int id_professor;
    private String nome;
    private int idade;
    private String sala;
    private String especialidade;

    public Professor(int id_professor, String nome, int idade, String sala, String especialidade){
        this.id_professor = id_professor;
        this.nome = nome;
        this.idade = idade;
        this.sala = sala;
        this.especialidade = especialidade;
    }
    public Professor(){
    }
    public int getId_professor() {
        return id_professor;
    }
    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public String toString(){
        return "Nome: " + nome + "Idade: " + idade + "Sala: " + sala + "Especialidade: " + especialidade; 
    }
}