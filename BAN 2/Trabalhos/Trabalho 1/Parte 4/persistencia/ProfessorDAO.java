package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Professor;

public class ProfessorDAO {
    private static ProfessorDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement calcularTempoTotal;

    public static ProfessorDAO getInstance(){
        if(instance == null){
            try {
                instance = new ProfessorDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private ProfessorDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("SELECT nextval('id_professor')");
        insert = connection.prepareStatement("INSERT INTO professor values (?, ?, ?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM professor");
        delete = connection.prepareStatement("DELETE FROM professor WHERE id_professor = ?");
        update = connection.prepareStatement("UPDATE professor SET nome = ?, idade = ?, sala = ?, especialidade = ? WHERE id_professor = ?");
        calcularTempoTotal = connection.prepareStatement("SELECT tempo_total_professor(?)");

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
    public void insert(Professor professor) throws SQLException{ 
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, professor.getNome());
            insert.setInt(3, professor.getIdade());
            insert.setString(4, professor.getSala());
            insert.setString(5, professor.getEspecialidade());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir professor");
        }
    }
    public List<Professor> selectAll() throws SQLException{
        List<Professor> professores = new LinkedList<Professor>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                int idade = rs.getInt(3);
                String sala = rs.getString(4);
                String especialidade = rs.getString(5);
                professores.add(new Professor(id, nome, idade, sala, especialidade));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar professores");
        }
        return professores;
    }
    public void delete(Professor professor) throws SQLException{
        try{
            delete.setInt(1, professor.getId_professor());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar professor");
        }
    }
    public void update(Professor professor) throws SQLException{
        try{
            update.setString(1, professor.getNome());
            update.setInt(2, professor.getIdade());
            update.setString(3, professor.getSala());
            update.setString(4, professor.getEspecialidade());
            update.setInt(5, professor.getId_professor());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar professor");
        }
    }
    public float calcularTempoTotal(int profID) throws SQLException {
        float tempoTotal = 0;
        try {
            calcularTempoTotal.setInt(1, profID); 
            ResultSet rs = calcularTempoTotal.executeQuery();
            if (rs.next()) {
                tempoTotal = rs.getFloat(1); 
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao calcular o tempo total de trabalho");
        }
        return tempoTotal;
    }
}    