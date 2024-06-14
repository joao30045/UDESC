package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Ator;

public class AtorDAO {
    private static AtorDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static AtorDAO getInstance(){
        if(instance == null){
            try {
                instance = new AtorDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private AtorDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("select nextval('id_ator')");
        insert = connection.prepareStatement("insert into ator values (?, ?, ?, ?)");
        selectAll = connection.prepareStatement("select * from ator");
        delete = connection.prepareStatement("delete from ator where id = ?");
        update = connection.prepareStatement("update ator set nome = ?, dataNascimento = ?, sexo = ?");
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
    public void insert(Ator ator) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, ator.getNome());
            insert.setString(3, ator.getDataNascimento());
            insert.setString(4, ator.getSexo());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir ator");
        }
    }
    public List<Ator> selectAll() throws SQLException{
        List<Ator> atores = new LinkedList<Ator>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String dataNascimento = rs.getString(3);
                String sexo = rs.getString(4);
                atores.add(new Ator(id, nome, dataNascimento, sexo));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao buscar ator");
        }
        return atores;
    }
    public void delete(Ator ator) throws SQLException{
        try{
            delete.setInt(1, ator.getId());
            delete.executeUpdate();
        } catch(SQLException e){
            throw new SQLException("Erro ao deletar ator");
        }
    }
    public void update(Ator ator) throws SQLException{
        try{
            update.setString(1, ator.getNome());
            update.setString(2, ator.getDataNascimento());
            update.setString(3, ator.getSexo());
            update.setInt(4, ator.getId());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar ator");
        }
    }
}

