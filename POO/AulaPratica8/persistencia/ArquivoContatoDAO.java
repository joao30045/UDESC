package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Contato;

public class ArquivoContatoDAO {
    private EditorTexto editorArquivo = new EditorTexto();
    private String caminho = " ";

    private String toCSV(Contato contato){
        String c = "";
        c += contato.getNome() + ",";
        c += contato.getTelefone();
        return c;
    }
    private Contato fromCSV(String s){
        String atributos[] = s.split(",");

        Contato c = new Contato();
        c.setNome(atributos[0]);
        c.setTelefone(Integer.parseInt(atributos[1]));
        return c;
    }
    public List<Contato> leContatos(){
        List<Contato> contatos = new LinkedList<Contato>();
        for(String linha : editorArquivo.leTexto(caminho)){
            contatos.add(fromCSV(linha));
        }
        return contatos;
    }
    public void salvarContatos(List<Contato> contatos) {
        List<String> linhas = new LinkedList<String>();
        for (Contato contato : contatos) {
            linhas.add(toCSV(contato));
        }
        editorArquivo.gravaTexto(caminho, linhas);

    }

    public void salvarContato(Contato contato) {
        editorArquivo.gravaTexto(caminho, toCSV(contato));
    }
}
