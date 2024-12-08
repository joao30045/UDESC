package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Departamento;

public class DepartamentoDAO {
    private static DepartamentoDAO instance = null;
    
    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static DepartamentoDAO getInstance(){
        if(instance == null){
            try {
                instance = new DepartamentoDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private DepartamentoDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("SELECT nextval('id_departamento')");
        insert = connection.prepareStatement("INSERT INTO departamento VALUES (?, ?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM departamento");
        delete = connection.prepareStatement("DELETE FROM departamento WHERE id_departamento = ?");
        update = connection.prepareStatement("UPDATE departamento SET nome = ?, escritorio_principal = ?, lider = ? WHERE id_departamento = ?");
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
    public void insert(Departamento departamento) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, departamento.getNome());
            insert.setString(3, departamento.getEscritorio_principal());
            insert.setInt(4, departamento.getLider());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir departamento");
        }
    }
    public List<Departamento> selectAll() throws SQLException{
        List<Departamento> departamentos = new LinkedList<Departamento>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String escritorio_principal = rs.getString(3);
                int lider = rs.getInt(4);
                departamentos.add(new Departamento(id, nome, escritorio_principal, lider)); 
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar departamentos");
        }
        return departamentos;
    }
    public void delete(Departamento departamento) throws SQLException{
        try{
            delete.setInt(1, departamento.getId_departamento());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar departamento");
        }
    }
    public void update(Departamento departamento) throws SQLException{
        try{
            update.setString(1, departamento.getNome());
            update.setString(2, departamento.getEscritorio_principal());
            update.setInt(3, departamento.getLider());
            update.setInt(4, departamento.getId_departamento());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar departamento");
        }
    }
}
