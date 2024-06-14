package ex3;

public class NomeInvalidoException extends Exception{
    public NomeInvalidoException(){

    }
    public NomeInvalidoException(String msg){
        super(msg);
    }
}
