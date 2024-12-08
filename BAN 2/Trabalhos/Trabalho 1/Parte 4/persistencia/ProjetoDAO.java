package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Projeto;

public class ProjetoDAO {
    private static ProjetoDAO instance = null;
    
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static ProjetoDAO getInstance(){
        if(instance == null){
            try {
                instance = new ProjetoDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private ProjetoDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        insert = connection.prepareStatement("INSERT INTO projeto VALUES (?, ?, ?, ?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM projeto");
        delete = connection.prepareStatement("DELETE FROM projeto WHERE id_projeto = ?");
        update = connection.prepareStatement("UPDATE projeto SET orgao_financiador = ?, data_inicio = ?, data_fim = ?, orcamento = ?, pesquisador_principal = ? WHERE id_projeto = ?");
    }


    public void insert(Projeto projeto) throws SQLException{
        try{
            insert.setInt(1, projeto.getId_projeto());
            insert.setString(2, projeto.getOrgao_financiador());
            insert.setString(3, projeto.getData_inicio());
            insert.setString(4, projeto.getData_fim());
            insert.setFloat(5, projeto.getOrcamento());
            insert.setInt(6, projeto.getPesquisador_principal());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir projeto" + e.getMessage());
        }
    }

    public List<Projeto> selectAll() throws SQLException{
        List<Projeto> projetos = new LinkedList<Projeto>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String orgao_financiador = rs.getString(2);
                String data_inicio = rs.getString(3);
                String data_fim = rs.getString(4);
                float orcamento = rs.getFloat(5);
                int pesquisador_principal = rs.getInt(6);
                projetos.add(new Projeto(id, orgao_financiador, data_inicio, data_fim, orcamento, pesquisador_principal)); 
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao consultar projetos");
        }
        return projetos;
    }

    public void delete(Projeto projeto) throws SQLException{
        try{
            delete.setInt(1, projeto.getId_projeto());
            delete.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao deletar projeto");
        }
    }

    public void update(Projeto projeto) throws SQLException{
        try{
            update.setString(1, projeto.getOrgao_financiador());
            update.setString(2, projeto.getData_inicio());
            update.setString(3, projeto.getData_fim());
            update.setFloat(4, projeto.getOrcamento());
            update.setInt(5, projeto.getPesquisador_principal());
            update.setInt(6, projeto.getId_projeto());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar projeto" + e.getMessage());
        }
    }
}