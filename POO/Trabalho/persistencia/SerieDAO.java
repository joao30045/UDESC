package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Conteudo;
import dados.Episodio;
import dados.Serie;

public class SerieDAO {
    private static SerieDAO instance = null;
    private static EpisodioDAO episodioDAO = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static SerieDAO getInstance(){
        if(instance == null){
            try {
                instance = new SerieDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private SerieDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("select nextval('id_serie')");
        insert = connection.prepareStatement("insert into serie values (?, ?, ?, ?, ?, ?)");
        selectAll = connection.prepareStatement("select * from serie");
        delete = connection.prepareStatement("delete from serie where id = ?");
        update = connection.prepareStatement("update serie set titulo = ?, ano = ?, temporada = ?, genero = ?, descricao = ? where id_serie = ?");

        episodioDAO = EpisodioDAO.getInstance();
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
    public void insert(Serie serie) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, serie.getTitulo());
            insert.setInt(3, serie.getAno());
            insert.setInt(4, serie.getTemporada());
            insert.setString(5, serie.getGenero());
            insert.setString(6, serie.getDescricao());
            insert.executeUpdate();
            serie.getEpisodio().setId_serie(serie.getId());
            episodioDAO.insert(serie.getEpisodio());
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir serie");
        }
    }
    public List<Conteudo> selectAll() throws SQLException{
        List<Conteudo> series = new LinkedList<Conteudo>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String titulo = rs.getString(2);
                int ano = rs.getInt(3);
                int temporada = rs.getInt(4);
                String genero = rs.getString(5);
                String descricao = rs.getString(6);
                series.add(new Serie(id, titulo, ano, temporada, genero, descricao));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao buscar serie");
        }
        return series;
    }
    public void delete(Serie serie) throws SQLException {
        try {
            EpisodioDAO episodioDAO = EpisodioDAO.getInstance();
            List<Episodio> episodios = episodioDAO.select(serie.getId());
            if(!episodios.isEmpty()){
                for (Episodio episodio : episodios) {
                    episodioDAO.delete(episodio);
                }
            }
            delete.setInt(1, serie.getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar série");
        }
    }
    public void update(Serie serie) throws SQLException{
        try{
            update.setString(1, serie.getTitulo());
            update.setInt(2, serie.getAno());
            update.setInt(3, serie.getTemporada());
            update.setString(4, serie.getGenero());
            update.setString(5, serie.getDescricao());
            update.setInt(6, serie.getId());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar serie");
        }
    }
}

