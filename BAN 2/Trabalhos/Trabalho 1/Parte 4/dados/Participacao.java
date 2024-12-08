package dados;

public class Participacao {
    private int assistentes_investigacao;
    private int numero_projeto;

    public Participacao(int assistentes_investigacao, int numero_projeto){
        this.assistentes_investigacao = assistentes_investigacao;
        this.numero_projeto = numero_projeto;
    }
    public Participacao(){
    }
    public int getAssistentes_investigacao() {
        return assistentes_investigacao;
    }
    public void setAssistentes_investigacao(int assistentes_investigacao) {
        this.assistentes_investigacao = assistentes_investigacao;
    }
    public int getNumero_projeto() {
        return numero_projeto;
    }
    public void setNumero_projeto(int numero_projeto) {
        this.numero_projeto = numero_projeto;
    }
    public String toString(){
        return "Assistente de investigacao: " + assistentes_investigacao + "Numero do projeto: " + numero_projeto;
    }
}
