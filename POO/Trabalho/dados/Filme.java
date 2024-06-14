package dados;

public class Filme extends Conteudo {
    private int duracao;

    public Filme(int id, String t, int ano, int duracao,  String g, String d){
        super(t, d, g, ano);
        this.duracao = duracao;
        this.id = id;
    }
    public Filme(){
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    @Override
    public String toString(){
        return super.toString() + "\nDuração: " + duracao + "\n";
    }
}
