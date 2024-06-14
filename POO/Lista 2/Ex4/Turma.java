import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Turma{
    private Scanner s = new Scanner(System.in);
    private List<Aluno> a = new ArrayList<Aluno>();

    public static void main(String[] args) {
        Turma main = new Turma();
        main.adicionarAluno(main.cadastrarAluno());
        main.ordenaAlunosPorMedia();
        System.out.println(main.separarEmEquipes(main.getA()));
    }
    public Aluno cadastrarAluno() {
        Aluno aux = new Aluno();
        System.out.println("Nome:");
        aux.setNome(s.next());
        System.out.println("Idade:");
        aux.setIdade(s.nextInt());
        double[] notas = new double[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Nota:");
            notas[i] = s.nextDouble();
            aux.setNotas(i, notas[i]);
        }
        return aux;
    }
    public void adicionarAluno(Aluno aluno){
        a.add(aluno);
    }
    private void ordenaAlunosPorMedia(){
        for (int i = 0; i < a.size(); i++)
            for (int j = 0; j < a.size()-1; j++)
                if (a.get(j).calcularMedia() > a.get(j+1).calcularMedia()) {
                    Aluno aux = a.get(j);
                    a.set(j,a.get(j+1));
                    a.set(j+1,aux);
                }
    }
    public List<Equipe<Aluno>> separarEmEquipes(List<Aluno> alunos){
        List<Equipe<Aluno>> listaFinal = new LinkedList<Equipe<Aluno>>();
        List<Aluno> al = alunos;
        int qtdAlunos = al.size();
        while(qtdAlunos > 4){
            Equipe<Aluno> team = new Equipe<Aluno>();
            List<Aluno> aux = new ArrayList<Aluno>();
            aux.add(al.get(0));
            al.remove(0);
            aux.add(al.get(1));
            al.remove(1);
            aux.add(al.get(qtdAlunos));
            al.remove(qtdAlunos);
            aux.add(al.get(qtdAlunos-1));
            al.remove(qtdAlunos-1);
            team.setAlunos(aux);
            team.setNome(s.next());
            listaFinal.add(team);
        }
        return listaFinal;
    }

    public List<Aluno> getA() {
        return a;
    }
}