package ex2;

public class Circulo extends FormaGeometrica{
    public float calculaArea(){
        return (float) (3.14 * medida1 * medida1);
    }
    public float calculaPerimetro(){
        return (float) (3.14 * medida2);
    }
    public void setRaio(int r){
        super.medida1 = r;
        super.medida2 = 2 * r;
    }
    public String toString(){
        return "Raio: " + super.medida1 + " Diametro: " + super.medida2 + " Area: " + calculaArea() + " Perimetro: " + calculaPerimetro();
    }
}
