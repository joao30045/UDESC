package ex1e2;

public class Main {
    public static void main(String[] args) {
        Revenda r1 = new Revenda("Matriz");
        Revenda r2 = new Revenda("Filial");

        r1.setCnpj("123");
        r2.setCnpj("124");

        System.out.println("Revendas:");
        System.out.println(r1.getNome() + " " + r1.getCnpj());
        System.out.println(r2.getNome() + " " + r2.getCnpj());

        Cliente c1 = new Cliente("123457");
        Cliente c2 = new Cliente("123456");

        c1.setNome("João");
        c2.setNome("Pedro");

        System.out.println("Clientes: ");
        System.out.println(c1.getNome() + " " + c1.getCpf());
        System.out.println(c2.getNome() + " " + c2.getCpf());

        Carro carro1 = new Carro("DEF-5678");
        Carro carro2 = new Carro("ABC-1234");

        carro1.setCor("Preta");
        carro2.setCor("Branca");

        System.out.println("Carros:");
        System.out.println(carro1.getPlaca() + " " + carro1.getCor());
        System.out.println(carro2.getPlaca() + " " + carro2.getCor());

        Moto moto1 = new Moto("ADB-2425");
        Moto moto2 = new Moto("JGF-0086");

        moto1.setCor("Azul");
        moto2.setCor("Vermelha");

        System.out.println("Motos: ");
        System.out.println(moto1.getPlaca() + " " + moto1.getCor());
        System.out.println(moto2.getPlaca() + " " + moto2.getCor());

        Aluguel a1 = new Aluguel(1);
        Aluguel a2 = new Aluguel(2);

        a1.setCliente(c1);
        a1.setCarro(carro1);

        a2.setCliente(c2);
        a2.setCarro(carro2);
        a2.setMoto(moto1);

        System.out.println("Algueis: ");
        System.out.println(a1.getCliente().getCpf() + " " + a1.getCarro().getPlaca());
        System.out.println(a2.getCliente().getCpf() + " " + a2.getCarro().getPlaca() + " " + a2.getMoto().getPlaca());

    }
}
