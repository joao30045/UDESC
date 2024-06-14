package ex1e2;

public class Revenda {
    private String nome;
    private String rua;
    private String cnpj;
    private String cidade;
    private int numero;
    private int cep;

    public Revenda(){
    }
    public Revenda(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getCep() {
        return cep;
    }
    public void setCep(int cep) {
        this.cep = cep;
    }
    public String toString(){
        return "Revenda: Nome: " + nome + ", CNPJ: " + cnpj;
    }
    public boolean equals(Object o){
        Revenda r;
        if(!(o instanceof Revenda)){
            return false;
        }
        r = (Revenda) o;
        if(cnpj.equals(r.getCnpj())){
            return true;
        }
        return false;
    }
}
