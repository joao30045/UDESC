package dados;

public class Animal {
    
    private String nome;
    private Dono dono;
    private String especie;
    private String descricao;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String toString(){

        String animal = "";

        animal += "Nome: " + this.nome + "\n";
        animal += "Espécie: " + this.especie + "\n";
        animal += "Descrição: " + this.descricao + "\n";

        return animal;
    }
}
