package dados;

public class Estudante {
    private int id_estudante;
    private String nome;
    private int idade;
    private String tipo_do_curso;
    private int depart;
    private int aconselhador;

    public Estudante(int id_estudante, String nome, int idade, String tipo_do_curso,
    int depart, int aconselhador){
        this.id_estudante = id_estudante;
        this.nome = nome;
        this.idade = idade;
        this.tipo_do_curso = tipo_do_curso;
        this.depart = depart;
        this.aconselhador = aconselhador;
    }
    public Estudante(){
    }
    public int getId_estudante() {
        return id_estudante;
    }
    public void setId_estudante(int id_estudante) {
        this.id_estudante = id_estudante;
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
    public String getTipo_do_curso() {
        return tipo_do_curso;
    }
    public void setTipo_do_curso(String tipo_do_curso) {
        this.tipo_do_curso = tipo_do_curso;
    }
    public int getDepart() {
        return depart;
    }
    public void setDepart(int depart) {
        this.depart = depart;
    }
    public int getAconselhador() {
        return aconselhador;
    }
    public void setAconselhador(int aconselhador) {
        this.aconselhador = aconselhador;
    }
    public String toString(){
        return "Nome: " + nome + "Idade: " + idade + "Tipo do curso: " + tipo_do_curso +
        "Departamento: " + depart + "Aconselhador: " + aconselhador;
    }
}
