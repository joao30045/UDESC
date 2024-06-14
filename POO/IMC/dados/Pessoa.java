package dados;

public class Pessoa {
    
    private String nome;
    private int idade;
    private float altura;
    private float massa; 

    public Pessoa() {
        
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome; 
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getAltura() {
        return altura;
    }

    public void setMassa(Float massa) {
        this.massa = massa;
    }

    public Float getMassa() {
        return massa;
    }

    public Float calculaImc() {
        return massa/(altura * altura);
    }

    public String toString(){
        return "Nome: "+nome+"\nAltura: "+altura+"\nIdade: "+idade+"\nMassa: "+massa+"\nIMC: "+calculaImc();
    }

}