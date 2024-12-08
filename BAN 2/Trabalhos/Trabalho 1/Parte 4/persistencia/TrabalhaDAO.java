package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Trabalha;

public class TrabalhaDAO {
    private static TrabalhaDAO instance = null;

    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static TrabalhaDAO getInstance(){
        if(instance == null){
            try {
                instance = new TrabalhaDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private TrabalhaDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        insert = connection.prepareStatement("INSERT INTO trabalha VALUES (?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM trabalha");
        delete = connection.prepareStatement("DELETE FROM trabalha WHERE (numero_departamento = ? AND professor_trabalha = ?)");
        update = connection.prepareStatement("UPDATE trabalha SET numero_professor = ?, professor_trabalha = ?, tempo = ? WHERE (numero_departamento = ? AND professor_trabalha = ?)");
    }

    public void insert(Trabalha trabalha) throws SQLException{
        try{
            insert.setInt(1, trabalha.getNumero_departamento());
            insert.setInt(2, trabalha.getProfessor_trabalha());
            insert.setFloat(3, trabalha.getTempo());
            
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir trabalha");
        }
    }
    
    public List<Trabalha> selectAll() throws SQLException{
        List<Trabalha> trabalham = new LinkedList<Trabalha>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int numero_departamento = rs.getInt(1);
                int professor_trabalha = rs.getInt(2);
                Float tempo = rs.getFloat(3);
                
                trabalham.add(new Trabalha(numero_departamento, professor_trabalha, tempo)); 
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar trabalha");
        }
        return trabalham;
    }
    public void delete(Trabalha trabalha) throws SQLException{
        try{
            delete.setInt(1, trabalha.getNumero_departamento());
            delete.setInt(2, trabalha.getProfessor_trabalha());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar departamento");
        }
    }
    public void update(Trabalha trabalha) throws SQLException{
        try{
            update.setInt(1, trabalha.getNumero_departamento());
            update.setInt(2, trabalha.getProfessor_trabalha());
            update.setFloat(3, trabalha.getTempo());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar departamento");
        }
    }

}
