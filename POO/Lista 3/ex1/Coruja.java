package ex1;

public class Coruja extends Animal{
    @Override
    public String emitirSom(){
        return this.getNome() + ": Pruu";
    }
}

