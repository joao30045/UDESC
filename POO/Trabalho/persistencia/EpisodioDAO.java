package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Episodio;

public class EpisodioDAO {
    private static EpisodioDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static EpisodioDAO getInstance(){
        if(instance == null){
                try {
                    instance = new EpisodioDAO();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
        return instance;
    }
    private EpisodioDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("select nextval('id_episodio')");
        insert = connection.prepareStatement("insert into episodio values (?, ?, ?, ?, ?, ?, ?)");
        select = connection.prepareStatement("select * from episodio where id_serie = ?");
        delete = connection.prepareStatement("delete from episodio where id = ?");
        update = connection.prepareStatement("update episodio set titulo = ?, numeroEpisodio = ?, numeroTemporada = ?, duracao = ?, descricao = ? where id_serie = ?");
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
    public void insert(Episodio episodio) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, episodio.getTitulo());
            insert.setInt(3, episodio.getNumeroEpisodio());
            insert.setInt(4, episodio.getNumeroTemporada());
            insert.setInt(5, episodio.getDuracao());
            insert.setString(6, episodio.getDescricao());
            insert.setInt(7, episodio.getId_serie());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir episodio");
        }
    }
    public List<Episodio> select(int serie) throws SQLException {
        List<Episodio> episodios = new ArrayList<>();
        try {
            select.setInt(1, serie);
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String titulo = rs.getString(2);
                int numeroEpisodio = rs.getInt(3);
                int numeroTemporada = rs.getInt(4);
                int duracao = rs.getInt(5);
                String descricao = rs.getString(6);
                Episodio episodio = new Episodio(id, titulo, numeroEpisodio, numeroTemporada, duracao, descricao);
                episodios.add(episodio);
            }
        } catch(SQLException e){
            throw new SQLException("Erro ao buscar episodios da serie");
        }
        return episodios;
    }
    public void delete(Episodio episodio) throws SQLException{
        try{
            delete.setInt(1, episodio.getId());
            delete.executeUpdate();
        } catch(SQLException e){
            throw new SQLException("Erro ao deletar episodio");
        }
    }
    public void update(Episodio episodio) throws SQLException{
        try{
            update.setString(1, episodio.getTitulo());
            update.setInt(2, episodio.getNumeroEpisodio());
            update.setInt(3, episodio.getNumeroTemporada());
            update.setInt(4, episodio.getDuracao());
            update.setString(5, episodio.getDescricao());
            update.setInt(6, episodio.getId_serie());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar episodio");
        }
    }
}

