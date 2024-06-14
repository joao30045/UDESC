package Classes;

public class Cais extends Local{
    private boolean temMercadoria;

    Barco barcoAtracado;

    public Cais(int id, String nome, String coordenadas, boolean temMercadoria, Barco barcoAtracado) {
        super(id, nome, coordenadas);
        this.temMercadoria = temMercadoria;
        this.barcoAtracado = barcoAtracado;
    }

    public Barco getBarcoAtracado() {
        return barcoAtracado;
    }

    public void setBarcoAtracado(Barco barcoAtracado) {
        this.barcoAtracado = barcoAtracado;
    }

    public boolean temMercadoria() {
        return temMercadoria;
    }

    public void setTemMercadoria(boolean temMercadoria) {
        this.temMercadoria = temMercadoria;
    }

    @Override
    public String toString() {
        return super.toString() + ", Mercadoria: " + this.temMercadoria + ", Barco: " + barcoAtracado.getNome();
    }
}
