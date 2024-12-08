package dados;

public class Participa {
    private int id_participa;
    private int id_prof_super;
    private int id_proj;
    private int id_estud;

    public Participa (int id_participa, int id_prof_super, int id_proj, int id_estud) {
        this.id_participa = id_participa;
        this.id_prof_super = id_prof_super;
        this.id_proj = id_proj;
        this.id_estud = id_estud;
    }
    public Participa(){
    }

    public int getId_participa() {
        return id_participa;
    }
    public int getId_prof_super() {
        return id_prof_super;
    }
    public int getId_proj() {
        return id_proj;
    }
    public int getId_estud() {
        return id_estud;
    }
    public void setId_participa(int id_participa) {
        this.id_participa = id_participa;
    }
    public void setId_prof_super(int id_prof_super) {
        this.id_prof_super = id_prof_super;
    }
    public void setId_proj(int id_proj) {
        this.id_proj = id_proj;
    }
    public void setId_estud(int id_estud) {
        this.id_estud = id_estud;
    }
    public String toString(){
        return "Id do estudante: " + id_estud + ", Id do projeto: " + id_proj + ", Id do professor supervisor: " + id_prof_super;
    }
}
