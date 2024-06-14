package apresentacao;

import javax.swing.table.AbstractTableModel;

import dados.CalculadoraEstatistica;

public class TabelaValores extends AbstractTableModel{
    private String[] colunas = {"Valores"};
    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();
    @Override
    public int getRowCount() {
        return calculadora.getValores().size();
    }
    @Override
    public int getColumnCount() {
        return 1;
    }
    public String getColumnName(int column){
        return colunas[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return calculadora.getValores().get(rowIndex);
    }
    public void adicionarValor(int valor){
        calculadora.adicionarValor(valor);
        this.fireTableStructureChanged();
    } 
    public void limpar(){
        calculadora.limparValores();
        this.fireTableStructureChanged();
    }
}
