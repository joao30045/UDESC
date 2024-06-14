package projeto.dados;

public class FormaPagamento {
    private String formaPagamento;
    private int qtd;

    public FormaPagamento(String formaPagamento, int qtd){
        this.formaPagamento = formaPagamento;
        this.qtd = qtd;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public int getQtd() {
        return qtd;
    }

    @Override
    public String toString() {
        return "Forma Pagamento: " + formaPagamento 
                + "\nQuantidade: " + qtd;
    }
}
