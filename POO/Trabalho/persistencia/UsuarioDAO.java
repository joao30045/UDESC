package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Usuario;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement selectAll;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static UsuarioDAO getInstance(){
        if(instance == null){
            try {
                instance = new UsuarioDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private UsuarioDAO() throws SQLException{
        Connection connection = DataBaseConnection.getConnection();
        selectNewId = connection.prepareStatement("select nextval('id_usuario')");
        insert = connection.prepareStatement("insert into usuario values (?, ?, ?, ?)");
        selectAll = connection.prepareStatement("select * from usuario");
        delete = connection.prepareStatement("delete from usuario where id = ?");
        update = connection.prepareStatement("update usuario set nome = ?, dataNascimento = ?, senha = ?");
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
    public void insert(Usuario usuario) throws SQLException{
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, usuario.getNome());
            insert.setString(3, usuario.getDataNascimento());
            insert.setString(4, usuario.getSenha());
            insert.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao inserir usuario");
        }
    }
    public List<Usuario> selectAll() throws SQLException{
        List<Usuario> usuarios = new LinkedList<Usuario>();
        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String dataNascimento = rs.getString(3);
                String senha = rs.getString(4);
                usuarios.add(new Usuario(id, nome, dataNascimento, senha));
            }
        } catch (SQLException e){
            throw new SQLException("Erro ao buscar usuario");
        }
        return usuarios;
    }
    public void delete(Usuario usuario) throws SQLException{
        try{
            delete.setInt(1, usuario.getId());
            delete.executeUpdate();
        } catch(SQLException e){
            throw new SQLException("Erro ao deletar usuario");
        }
    }
    public void update(Usuario usuario) throws SQLException{
        try{
            update.setString(1, usuario.getNome());
            update.setString(2, usuario.getDataNascimento());
            update.setString(3, usuario.getSenha());
            update.setInt(4, usuario.getId());
            update.executeUpdate();
        } catch (SQLException e){
            throw new SQLException("Erro ao atualizar usuario");
        }
    }
}
