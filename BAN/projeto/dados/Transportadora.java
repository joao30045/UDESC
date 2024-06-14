package projeto.dados;

import java.text.DecimalFormat;

public class Transportadora {
    private int codTransportadora;
    private String nome;
    private String cnpj;
    private String email;
    private float custoKM;


    public Transportadora(int codTransportadora, String nome, String cnpj,  String email, float custoKM){
        this.codTransportadora = codTransportadora;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.custoKM = custoKM;
    }

    public Transportadora(String nome, float custoKM){
        this.nome = nome;
        this.custoKM = custoKM;
    }

    public Transportadora(){

    }

    public int getCodTransportadora() {
        return codTransportadora;
    }

    public void setCodTransportadora(int codTransportadora) {
        this.codTransportadora = codTransportadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCustoKM() {
        return custoKM;
    }

    public void setCustoKM(float custoKM) {
        this.custoKM = custoKM;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Codigo Transportadora: " + codTransportadora +
               "\nNome: " + nome + 
               "\nCNPJ: " + cnpj + 
               "\nEmail: " + email +
               "\nCusto KM: R$" + df.format(custoKM);
    }

}
