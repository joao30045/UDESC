import java.util.Arrays;

public class Aluno {
    private String nome;
    private int idade;
    double notas[] = new double[5];

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNotas(int i, double[] notas) {
        this.notas = notas;
    }
    public double calcularMedia(){
        double somaNotas = 0.0;
        for(double i : notas)
            somaNotas += i;
        return somaNotas /= 5;
    }
    public String toString(){
        return "Nome: " + nome + " Idade: " + idade + " Notas: " + Arrays.toString(notas);
    }
    public void setNotas(int i, double d) {
    }
}