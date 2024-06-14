package ex2;

public class Main {
    public static void main(String[] args) {
        TrianguloEquilatero t = new TrianguloEquilatero();
        t.setLado(5);
        TrianguloEquilatero t2 = new TrianguloEquilatero();
        t2.setLado(3);
        Circulo c = new Circulo();
        c.setRaio(4);
        Circulo c2 = new Circulo();
        c2.setRaio(2);
        Losango l = new Losango();
        l.setD(5);
        l.setd(3);
        Losango l2 = new Losango();
        l2.setD(4);
        l2.setd(2);

        System.out.println("Triangulos equilateros");
        System.out.println(t);
        System.out.println(t2);
        System.out.println("Circulos");
        System.out.println(c);
        System.out.println(c2);
        System.out.println("Losangos");
        System.out.println(l);
        System.out.println(l2);
    }
}
