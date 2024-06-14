package projeto.dados;

public class TransportadoraQuantidade {
    private String nome;
    private int quantidade;

    public TransportadoraQuantidade(String nome, int quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nQuantidade: " + quantidade;
    }
}
