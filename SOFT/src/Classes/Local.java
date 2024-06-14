package Classes;

public class Local {
    private int id;
    private String nome, coordenadas;

    public Local(int id, String nome, String coordenadas) {
        super();
        this.id = id;
        this.nome = nome;
        this.coordenadas = coordenadas;
    }

    public Local(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String toString() {
        return this.id + " - " + this.nome + ", Coordenadas: " + this.coordenadas;
    }
}