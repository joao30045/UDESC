package dados;

public class Projeto {
    private int id_projeto;
    private String orgao_financiador;
    private String data_inicio;
    private String data_fim;
    private float orcamento;
    private int pesquisador_principal;

    public Projeto(int id_projeto, String orgao_financiador, String data_inicio, String data_fim,
    float orcamento, int pesquisador_principal){
        this.id_projeto = id_projeto;
        this.orgao_financiador = orgao_financiador;
        this. data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.orcamento = orcamento;
        this.pesquisador_principal = pesquisador_principal;
    }
    public Projeto(){
    }
    public int getId_projeto() {
        return id_projeto;
    }
    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }
    public String getOrgao_financiador() {
        return orgao_financiador;
    }
    public void setOrgao_financiador(String orgao_financiador) {
        this.orgao_financiador = orgao_financiador;
    }
    public String getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }
    public String getData_fim() {
        return data_fim;
    }
    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
    public float getOrcamento() {
        return orcamento;
    }
    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }
    public int getPesquisador_principal() {
        return pesquisador_principal;
    }
    public void setPesquisador_principal(int pesquisador_principal) {
        this.pesquisador_principal = pesquisador_principal;
    }
    public String toString(){
        return "Orgao financiador: " + orgao_financiador + "Data de inicio: " + data_inicio + 
        "Data de fim: " + data_fim + "Orcamento: " + orcamento + "Pesquisador principal: " + pesquisador_principal;
    }
}
