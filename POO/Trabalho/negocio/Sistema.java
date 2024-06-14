package negocio;

import dados.*;
import persistencia.*;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sistema {
    private FilmeDAO filmeDAO = FilmeDAO.getInstance();
    private SerieDAO serieDAO = SerieDAO.getInstance();
    private AtorDAO atorDAO = AtorDAO.getInstance();
    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

    public void inserirFilme(Filme filme) throws SQLException{
        filmeDAO.insert(filme);
    }
    public void deletarFilme(Filme filme) throws SQLException{
        filmeDAO.delete(filme);
    }
    public void atualizarFilme(Filme filme) throws SQLException{
        filmeDAO.update(filme);
    }
    public List<Conteudo> getAllConteudo() throws SQLException{
        List<Conteudo> all = new ArrayList<>();
        List<Conteudo> filmes = filmeDAO.selectAll();
        List<Conteudo> series = serieDAO.selectAll();
        all.addAll(filmes);
        all.addAll(series);
        return all;
    }
    public void inserirSerie(Serie s) throws SQLException{
        serieDAO.insert(s);
    }
    public void deletarSerie(Serie s) throws SQLException{
        serieDAO.delete(s);
    }
    public void atualizarSerie(Serie s) throws SQLException{
        serieDAO.update(s);
    }
    public List<Conteudo> selectAllSeries() throws SQLException{
        return serieDAO.selectAll();
    }
    public List<Ator> selectAll() throws SQLException{
        return atorDAO.selectAll();
    }
    public void inserirAtor(Ator a) throws SQLException{
        atorDAO.insert(a);
    }
    public void deletarAtor(Ator a) throws SQLException{
        atorDAO.delete(a);
    }
    public void atualizarAtor(Ator a) throws SQLException{
        atorDAO.update(a);
    }
    public void inserirUsuario(Usuario u) throws SQLException{
        usuarioDAO.insert(u);
    }
    public void atualizarUsuario(Usuario u) throws SQLException{
        usuarioDAO.update(u);
    }
}
