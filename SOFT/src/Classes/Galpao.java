package Classes;

public class Galpao extends Local{
    private int qtdMercadoria;

    public Galpao(int id, String nome, String coordenadas, int qtdMercadoria) {
        super(id, nome, coordenadas);
        this.qtdMercadoria = qtdMercadoria;
    }

    public int getQtdMercadoria() {
        return qtdMercadoria;
    }

    public void setQtdMercadoria(int qtdMercadoria) {
        this.qtdMercadoria = qtdMercadoria;
    }

    @Override
    public String toString() {
        return super.toString() + ", Quantidade de mercadoria: " + this.qtdMercadoria;
    }
}
