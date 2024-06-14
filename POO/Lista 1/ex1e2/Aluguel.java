package ex1e2;

import java.util.Objects;

public class Aluguel {
    private Cliente cliente;
    private Carro carro;
    private Moto moto;
    private int codigoAluguel;

    public Aluguel(){
    }
    public Aluguel(int codigoAluguel){
        this.codigoAluguel = codigoAluguel;
    }
    public int getCodigoAluguel() {
        return codigoAluguel;
    }
    public void setCodigoAluguel(int codigoAluguel) {
        this.codigoAluguel = codigoAluguel;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Carro getCarro() {
        return carro;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public Moto getMoto() {
        return moto;
    }
    public void setMoto(Moto moto) {
        this.moto = moto;
    }
    public String toString(){
        return "Aluguel: Clientes: " + cliente + ", Carro: " + carro + ", Moto: " + moto;
    }
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Aluguel other = (Aluguel) obj;
        return Objects.equals(carro, other.carro) && Objects.equals(cliente, other.cliente) && Objects.equals(moto, other.moto);
    }
}
