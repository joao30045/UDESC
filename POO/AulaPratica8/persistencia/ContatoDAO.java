package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;

public class ContatoDAO {
    private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

    public void adicionarContato(Contato contato){
        arquivoContatoDAO.salvarContato(contato);
    }
    public void removerContato(Contato contato){
        List<Contato> contatos = arquivoContatoDAO.leContatos();
        contatos.remove(contato);
        arquivoContatoDAO.salvarContatos(contatos);
    }
    public Map<Character, List<Contato>> getAll(){
        Map<Character, List<Contato>> mapContatos = new HashMap<Character, List<Contato>>();

        for(char i = 65; i < 91; i++){
            List<Contato> lista = new LinkedList<Contato>();
            mapContatos.put(i, lista);
        }
        List<Contato> listContatos = arquivoContatoDAO.leContatos();
        for(Contato c : listContatos){
            String nome = c.getNome().toUpperCase();
            List<Contato> contatosTemp = mapContatos.get(nome.charAt(0));
            contatosTemp.add(c);
        }
        return mapContatos;
    }
}
