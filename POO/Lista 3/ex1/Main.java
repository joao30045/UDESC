package ex1;

public class Main {
    public static void main(String[] args) {

        Animal c = new Cachorro();
        c.setNome("Rex");
        Animal c2 = new Cachorro();
        c2.setNome("Shimu");
        Animal coruja = new Coruja();
        coruja.setNome("Astolfo");
        Animal coruja2 = new Coruja();
        coruja2.setNome("Frederico");
        Animal p = new Porco();
        p.setNome("Babe");
        Animal p2 = new Porco();
        p2.setNome("Peppa");

        System.out.println(c.emitirSom());
        System.out.println(c2.emitirSom());
        System.out.println(coruja.emitirSom());
        System.out.println(coruja2.emitirSom());
        System.out.println(p.emitirSom());
        System.out.println(p2.emitirSom());
    }
}
