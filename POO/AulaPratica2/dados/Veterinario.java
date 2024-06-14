package dados;

import dados.Animal;

public class Veterinario {
    
    private String nome;
    private float salario;
    private Endereco endereco;
    private Animal[] animais = new Animal[30];
    private int quantAnimais = 0;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Animal[] getAnimais() {
        return animais;
    }
    public void setAnimais(Animal animal) {
        if(this.quantAnimais < 30){
        this.animais[this.quantAnimais] = animal;
        this.quantAnimais++;
        }
    }
    public int getQuantAnimais() {
        return quantAnimais;
    }
    public String toString(){

        String veterinario = "";

        veterinario += "Nome: " + this.nome + "\n";
        veterinario += "Salario: " + this.salario + "\n";

        if(this.endereco != null){
            veterinario += "Endereço: " + this.endereco.toString() + "\n";
        }

        return veterinario;
    }
}
