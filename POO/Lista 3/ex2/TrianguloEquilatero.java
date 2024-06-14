package ex2;

import static java.lang.Math.sqrt;

public class TrianguloEquilatero extends FormaGeometrica{
    public float calculaArea(){
        return (float) ((sqrt(3) * medida1 * medida2) / 4); 
    }
    public float calculaPerimetro(){
        return 3 * medida1;
    }
    public void setLado(int l){
        super.medida1 = l;
        super.medida2 = l;
    }
    public String toString(){
        return "Medida do lado: " + super.medida1 + " Area: " + calculaArea() + " Perimetro: " + calculaPerimetro();
    }
}
