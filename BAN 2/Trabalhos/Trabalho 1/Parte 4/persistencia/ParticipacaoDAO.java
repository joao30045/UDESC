package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Participacao;

public class ParticipacaoDAO {
    private static ParticipacaoDAO instance = null;
    
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;

    public static ParticipacaoDAO getInstance(){
        if(instance == null){
            try {
                instance = new ParticipacaoDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private ParticipacaoDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        insert = connection.prepareStatement("INSERT INTO participacao VALUES (?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM participacao");
        delete = connection.prepareStatement("DELETE FROM participacao WHERE (assistentes_investigacao = ? AND numero_projeto = ?)");
    }


    public void insert(Participacao participacao) throws SQLException{
        try{
            insert.setInt(1, participacao.getAssistentes_investigacao());
            insert.setInt(2, participacao.getNumero_projeto());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir participacao");
        }
    }

    public List<Participacao> selectAll() throws SQLException{
        List<Participacao> participacoes = new LinkedList<Participacao>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int assistentes_investigacao = rs.getInt(1);
                int numero_projeto = rs.getInt(2);
                participacoes.add(new Participacao(assistentes_investigacao, numero_projeto)); 
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar participacoes");
        }
        return participacoes;
    }

    public void delete(Participacao participacao) throws SQLException{
        try{
            delete.setInt(1, participacao.getAssistentes_investigacao());
            delete.setInt(2, participacao.getNumero_projeto());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar participacao");
        }
    }

}
