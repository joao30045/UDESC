package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.Pessoa;
import persistencia.Conexao;
import persistencia.DeleteException;
import persistencia.InsertException;
import persistencia.PessoaDAO;
import persistencia.SelectException;
import persistencia.UpdateException;

public class Sistema {
    private PessoaDAO pessoaDAO;

    public Sistema(String senha) throws ClassNotFoundException, SQLException, SelectException{
        Conexao.setSenha(senha);
        pessoaDAO = PessoaDAO.getInstance();
    }
    public void inserirPessoa(Pessoa p) throws InsertException, SelectException{
        pessoaDAO.insert(p);
    }
    public List<Pessoa> selectAll() throws SelectException{
        return pessoaDAO.selectAll();
    }
    public void deletePessoa(Pessoa p) throws DeleteException{
        pessoaDAO.delete(p);
    }
    public void atualizarPessoa(Pessoa p) throws UpdateException{
        pessoaDAO.update(p);
    }
}
