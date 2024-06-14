package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection(){
        if(connection == null){
            String url = "jdbc:postgresql://localhost:5432/catalogo";
            String username = "postgres";
            String password = "postgres";
            try{
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch(ClassNotFoundException c){
                System.err.println("Driver nao encontrado");
            } catch(SQLException e){
                System.err.println("Nao foi possivel conectar");
            }
        }
        return connection;
    }
}
