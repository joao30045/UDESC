package dados;

public class Episodio {
    private int id;
    private int duracao;
    private int numeroEpisodio;
    private int numeroTemporada;
    private int id_serie;
    private String descricao;
    private String titulo;

    public Episodio(int id2, String titulo, int numeroEpisodio2, int numeroTemporada2, int duracao2, String descricao2){
        this.id = id2;
        this.titulo = titulo;
        this.numeroEpisodio = numeroEpisodio2;
        this.numeroTemporada = numeroTemporada2;
        this.duracao = duracao2;
        this.descricao = descricao2;
    }
    public Episodio(){
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getId_serie() {
        return id_serie;
    }
    public void setId_serie(int id_serie) {
        this.id_serie = id_serie;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String toString(){
        return  "Temporada: " + numeroTemporada + "Episodio: " + numeroEpisodio + "Titulo: " + titulo + "Duracao: " + duracao;
    }
}
