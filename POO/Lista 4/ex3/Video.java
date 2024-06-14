package ex3;

public class Video extends Arquivo{
    private Qualidade qualidade;

    public Qualidade getQualidade() {
        return qualidade;
    }
    public void setQualidade(Qualidade qualidade) {
        this.qualidade = qualidade;
    }
    public Video(String nome) throws NomeInvalidoException {
        super(nome);
        if(nome.contains("\n") || nome.contains("[") || nome.contains("]") || nome.contains("(") || nome.contains(")") ||
                nome.contains("\'") || nome.contains("\"") || nome.length() < 10 || nome.length() > 256)
            throw new NomeInvalidoException("Nome invalido");
        this.nome = nome;
        this.extensao = ".mp4";
    }
    public String toString() {
        return "Nome: "+ this.nome + "\n" + "Extensão: "+ this.extensao + "\n" + "Qualidade: " + this.qualidade + "\n";
    }
}
