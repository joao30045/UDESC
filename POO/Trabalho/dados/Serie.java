package dados;

public class Serie extends Conteudo{
    private int temporada;
    private Episodio episodio;
    private int id;

    public Serie(int id,String titulo, int ano, int temporada, String genero, String descricao){
        super(titulo, genero, descricao, ano);
        this.temporada = temporada;
        this.id = id;
    }
    public Serie() {
    }
    public int getTemporada() {
        return temporada;
    }
    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }
    public Episodio getEpisodio() {
        return episodio;
    }
    public void setEpisodio(Episodio episodio) {
        this.episodio = episodio;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString(){
        return super.toString() + "\nTemporadas: " + temporada + "\n";
    }
}