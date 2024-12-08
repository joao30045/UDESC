package dados;

public class Trabalha {
    private int numero_departamento;
    private int professor_trabalha;
    private float tempo;

    public Trabalha(int numero_departamento, int professor_trabalha, float tempo){
        this.numero_departamento = numero_departamento;
        this.professor_trabalha = professor_trabalha;
        this.tempo = tempo;
    }
    public Trabalha(){
    }
    public int getNumero_departamento() {
        return numero_departamento;
    }
    public void setNumero_departamento(int numero_departamento) {
        this.numero_departamento = numero_departamento;
    }
    public int getProfessor_trabalha() {
        return professor_trabalha;
    }
    public void setProfessor_trabalha(int professor_trabalha) {
        this.professor_trabalha = professor_trabalha;
    }
    public float getTempo() {
        return tempo;
    }
    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
    public String toString(){
        return "Departamento: " + numero_departamento + "Professor: " + professor_trabalha + "Tempo: " + tempo;
    }
}
