package dados;

public class Trabalha {
    private int id_trabalha;
    private int id_prof;
    private int id_proj;
   

    public Trabalha (int id_trabalha, int id_prof, int id_proj) {
        this.id_trabalha = id_trabalha;
        this.id_prof = id_prof;
        this.id_proj = id_proj;
    }
    public Trabalha(){
    }

    public int getId_trabalha() {
        return id_trabalha;
    }
    public int getId_prof() {
        return id_prof;
    }
    public int getId_proj() {
        return id_proj;
    }
    public void setId_trabalha(int id_trabalha) {
        this.id_trabalha = id_trabalha;
    }
    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }
    public void setId_proj(int id_proj) {
        this.id_proj = id_proj;
    }

    public String toString(){
        return "Id do professor: " + id_prof + ", Id do projeto: " + id_proj;
    }
}

