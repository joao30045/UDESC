public enum Cor {

    ROXA("roxa"), AZUl("azul"), VERDE("verde"), VERMELHA("vermelha"), BRANCA("branca"), PRETA("preta");

    private String cor;
    
    private Cor(String cor){
        this.cor = cor;
    }
    public String getCor(){
        return cor;
    }
}
