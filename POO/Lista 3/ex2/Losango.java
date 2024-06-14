package ex2;

import static java.lang.Math.sqrt;

public class Losango extends FormaGeometrica{
    public float calculaArea(){
        return (float) ((medida1 * medida2) / 2);
    }
    public float calculaPerimetro(){
        return (float) (4 * (sqrt((medida1 * medida1) / 4 + (medida2 * medida2) / 4)));
    }
    public void setD(int d){
        super.medida1 = d;
    }
    public void setd(int d){
        super.medida2 = d;
    }
    public String toString(){
        return "Diagonal maior: " + super.medida1 + " Diagonal menor: " + super.medida2 + " Area: " + calculaArea() + " Perimetro: " + calculaPerimetro();
    }
}
