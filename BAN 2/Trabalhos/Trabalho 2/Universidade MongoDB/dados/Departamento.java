package dados;

public class Departamento {
    private int id_departamento;
    private String nome;
    private String escritorio_principal;
    private int lider;

    public Departamento(int id_departamento, String nome, String escritorio_principal, int lider){
        this.id_departamento = id_departamento;
        this.nome = nome;
        this.escritorio_principal = escritorio_principal;
        this.lider = lider;
    }
    public Departamento(){
    }
    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEscritorio_principal() {
        return escritorio_principal;
    }
    public void setEscritorio_principal(String escritorio_principal) {
        this.escritorio_principal = escritorio_principal;
    }
    public int getLider() {
        return lider;
    }
    public void setLider(int lider) {
        this.lider = lider;
    }
    public String toString(){
        return "Nome: " + nome + "Escritorio principal: " + escritorio_principal + "Lider: " + lider;
    }
}
