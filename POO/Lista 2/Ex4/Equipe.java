import java.util.ArrayList;
import java.util.List;

public class Equipe<T> {
    private String nome;
    private List<Aluno> alunos = new ArrayList<Aluno>();

    public void setAlunos(List<Aluno> alunos){
        this.alunos = alunos;
    }
    public List<Aluno> getAlunos(){
        return alunos;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String toString(){
        return "Equipe: " + nome;
    }
}
