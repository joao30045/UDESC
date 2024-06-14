package dados;

public abstract class Conteudo {
    protected int id;
    protected int ano;
    protected String titulo;
    protected String genero;
    protected String descricao;
    protected String elencoPrincipal;
    protected String elencoSecundario;

    public Conteudo(String t, String g, String d, int ano){
        this.titulo = t;
        this.ano = ano;
        this.genero = g;
        this.descricao = d;
    }
    public Conteudo(){
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getElencoPrincipal() {
        return elencoPrincipal;
    }
    public void setElencoPrincipal(String elencoPrincipal) {
        this.elencoPrincipal = elencoPrincipal;
    }
    public String getElencoSecundario() {
        return elencoSecundario;
    }
    public void setElencoSecundario(String elencoSecundario) {
        this.elencoSecundario = elencoSecundario;
    }
    @Override
    public String toString(){
        return "\nTítulo: " + titulo + "\nAno de lançamento: " + ano + "\nGênero: " + genero + "\nDescrição: " + descricao + "\nElenco principal: " + elencoPrincipal + "\nElenco secundário: " + elencoSecundario; 
    }
}
