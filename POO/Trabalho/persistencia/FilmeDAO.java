package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Conteudo;
import dados.Filme;

public class FilmeDAO {
    private static FilmeDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static FilmeDAO getInstance(){
        if(instance == null){
            try {
                instance = new FilmeDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private FilmeDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("select nextval('id_filme')");
        insert = connection.prepareStatement("insert into filme values (?, ?, ?, ?, ?, ?)");
        selectAll = connection.prepareStatement("select * from filme");
        delete = connection.prepareStatement("delete from filme where id = ?");
        update = connection.prepareStatement("update filme set titulo = ?, ano = ?, duracao = ?, genero = ?, descricao = ?");
    }
    private int selectNewId() throws SQLException{
        try{
            ResultSet rs = selectNewId.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch(SQLException e){
            throw new SQLException("Erro ao buscar no id na tabela");
        }
        return 0;
    }
    public void insert(Filme filme) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, filme.getTitulo());
            insert.setInt(3, filme.getAno());
            insert.setInt(4, filme.getDuracao());
            insert.setString(5, filme.getGenero());
            insert.setString(6, filme.getDescricao());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir filme");
        }
    }
    public List<Conteudo> selectAll() throws SQLException{
        List<Conteudo> filmes = new LinkedList<Conteudo>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String titulo = rs.getString(2);
                int ano = rs.getInt(3);
                int duracao = rs.getInt(4);
                String genero = rs.getString(5);
                String descricao = rs.getString(6);
                filmes.add(new Filme(id, titulo, ano, duracao, genero, descricao));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao buscar filme");
        }
        return filmes;
    }
    public void delete(Filme filme) throws SQLException{
        try{
            delete.setInt(1, filme.getId());
            delete.executeUpdate();
        } catch(SQLException e){
            throw new SQLException("Erro ao deletar filme");
        }
    }
    public void update(Filme filme) throws SQLException{
        try{
            update.setString(1, filme.getTitulo());
            update.setInt(2, filme.getAno());
            update.setInt(3, filme.getDuracao());
            update.setString(4, filme.getGenero());
            update.setString(5, filme.getDescricao());
            update.setInt(6, filme.getId());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar filme");
        }
    }
}
