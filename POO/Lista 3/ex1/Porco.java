package ex1;

public class Porco extends Animal{
    @Override
    public String emitirSom(){
        return this.getNome() + ": Oinc-oinc";
    }
}

