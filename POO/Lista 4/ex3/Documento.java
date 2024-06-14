package ex3;

public class Documento extends Arquivo{
    private String texto;

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Documento(String nome) throws NomeInvalidoException {
        super(nome);
        if(nome.contains("\n") || nome.contains("[") || nome.contains("]") || nome.contains("(") || nome.contains(")") ||
        nome.contains("\'") || nome.contains("\"") || nome.length() < 10 || nome.length() > 256)
            throw new NomeInvalidoException("Nome Inválido");
        this.nome = nome;
        this.extensao = ".txt";
    }
    public String toString() {
        return "Nome: " + this.nome + "\n" + "Extensão: " + this.extensao + "\n" + "Conteúdo: " + this.texto + "\n";      
    }
}
