package Classes;

public class Estaleiro extends Local{
    Barco barco;

    public Estaleiro(int id, String nome, String coordenadas, Barco barco) {
        super(id, nome, coordenadas);
        this.barco = barco;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    @Override
    public String toString() {
        return super.toString() + ", Barco:" + barco.getNome();
    }
}
