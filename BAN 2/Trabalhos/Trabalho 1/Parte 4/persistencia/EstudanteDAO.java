package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Estudante;

public class EstudanteDAO {
    private static EstudanteDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static EstudanteDAO getInstance(){
        if(instance == null){
            try {
                instance = new EstudanteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private EstudanteDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("SELECT nextval('id_estudante')");
        insert = connection.prepareStatement("INSERT INTO estudante VALUES (?, ?, ?, ?, ?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM estudante");
        delete = connection.prepareStatement("DELETE FROM estudante WHERE id_estudante = ?");
        update = connection.prepareStatement("UPDATE estudante SET nome = ?, idade = ?, tipo_do_curso = ?, depart = ?, supervisor = ?, aconselhador = ? WHERE id_estudante = ?");          
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
    public void insert(Estudante estudante) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, estudante.getNome());
            insert.setInt(3, estudante.getIdade());
            insert.setString(4, estudante.getTipo_do_curso());
            insert.setInt(5, estudante.getDepart());
            insert.setInt(6, estudante.getSupervisor());
            insert.setInt(7, estudante.getAconselhador());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir estudante" + e.getMessage());
        }
    }
    public List<Estudante> selectAll() throws SQLException{
        List<Estudante> estudantes = new LinkedList<Estudante>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                int idade = rs.getInt(3);
                String tipo_do_curso = rs.getString(4);
                int depart = rs.getInt(5);
                int supervisor = rs.getInt(6);
                int aconselhador = rs.getInt(7);
                estudantes.add(new Estudante(id, nome, idade, tipo_do_curso, depart, supervisor, aconselhador));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar estudantes");
        }
        return estudantes;
    }
    public void delete(Estudante estudante) throws SQLException{
        try{
            delete.setInt(1, estudante.getId_estudante());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar estudante");
        }
    }
    public void update(Estudante estudante) throws SQLException{
        try{
            update.setString(1, estudante.getNome());
            update.setInt(2, estudante.getIdade());
            update.setString(3, estudante.getTipo_do_curso());
            update.setInt(4, estudante.getDepart());
            update.setInt(5, estudante.getSupervisor());
            update.setInt(6, estudante.getAconselhador());
            update.setInt(7, estudante.getId_estudante());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar estudante" + e.getMessage());
        }
    }
}
