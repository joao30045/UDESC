package projeto.dados;

import java.sql.Date;

public class PessoaFisica extends Clientes {
    private Date dataNascimento;
    private String sexo;
    private String cpf;

    public PessoaFisica(int codCliente, int tipo, String nome, Date dataNascimento, String sexo, String email, String telefone, 
    String rua, String bairro, int cep, String cpf){
        super(codCliente, email, telefone, nome, rua, cep, bairro, tipo);
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, int cep){
        super(nome, cep);
    }

    public Date getDataNacimento() {
        return dataNascimento;
    }
    
    public void setDataNacimento(Date dataNacimento) {
        this.dataNascimento = dataNacimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return super.toString() 
        + "\nSexo: " + sexo
        + "\nData de Nascimento: " + dataNascimento
        + "\nCPF: " + cpf; 
    }
}
