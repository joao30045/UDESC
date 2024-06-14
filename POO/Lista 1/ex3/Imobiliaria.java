package ex3;

import java.util.Arrays;

public class Imobiliaria {
    private String nome;
    private Imovel imoveis[];
    private int quantidadeImoveis;

    public Imobiliaria(int quantidadeImoveis){
        imoveis = new Imovel[quantidadeImoveis];
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Imovel[] getImoveis() {
        return imoveis;
    }
    public void setImoveis(Imovel[] imoveis) {
        this.imoveis = imoveis;
    }
    public int getQuantidadeImoveis() {
        return quantidadeImoveis;
    }
    public void setQuantidadeImoveis(int quantidadeImoveis) {
        this.quantidadeImoveis = quantidadeImoveis;
    }
    public void adicionaImovel(Imovel imovel){
        quantidadeImoveis++;
        imoveis[quantidadeImoveis] = imovel;
    }
    public Imovel[] filtrarImoveis(float x){
        Imovel[] imoveis = this.getImoveis();
        Imovel[] imoveisMaiores = new Imovel[quantidadeImoveis];
        int contador = 0;
        for(int i = 0; i < imoveis.length; i++){
            if(imoveis[i].areaImovel() > x){
                imoveisMaiores[contador] = imoveis[i];
                contador++;
            }
        }
        for(int i = 0; i < imoveisMaiores.length; i++){
            for(int j = 0; j < (imoveisMaiores.length) - 1; j++){
                Imovel aux = imoveisMaiores[i];
                imoveisMaiores[i] = imoveisMaiores[i + 1];
                imoveisMaiores[i + 1] = aux;
            }
        }
        return imoveisMaiores;
    }
    public String toString(){
        return "Imobiliaria: Nome: " + nome + ", Imoveis: " + Arrays.toString(imoveis); 
    }
}
