package projeto.dados;

public class Clientes{
    
    private int codCliente;
    private String email;
    private String telefone;
    private String nome;
    private String rua;
    private int cep;
    private String bairro;
    private int tipo;
    
    public Clientes(int codCliente, String email, String telefone, String nome, String rua, int cep, String bairro, int tipo){
        this.codCliente = codCliente; 
        this.email = email;               
        this.telefone = telefone;
        this.nome = nome;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.tipo = tipo;      
    }

    public Clientes(String nome, int cep){
        this.nome = nome;
        this.cep = cep;
    }

    public Clientes(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    public Clientes(String nome, String email, int tipo){
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public int getCodCliente() {
        return codCliente;
    }
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    public int getCep() {
        return cep;
    }
    public void setCep(int cep) {
        this.cep = cep;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Codigo do Cliente: " + codCliente 
        + "\nNome: " + nome 
        + "\nEmail: " + email
        + "\nTelefone: " + telefone
        + "\nRua: " + rua
        + "\nCEP: " + cep
        + "\nBairro: " + bairro;
    }
}