package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import projeto.dados.Transportadora;
import projeto.dados.TransportadoraQuantidade;

public class TransportadoraModel {
    public static void create(Transportadora t, Connection con) throws SQLException{
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO transportadora (nome, cnpj, email, custokm)" + "VALUES (?, ?, ?, ?)");

            st.setString(1, t.getNome());
            st.setString(2, t.getCnpj());
            st.setString(3, t.getEmail());
            st.setDouble(4, t.getCustoKM());
            st.execute();
            st.close();
    }

public static ArrayList<Transportadora> listAll(Connection con) throws SQLException{
    Statement st;
    ArrayList<Transportadora> list = new ArrayList<Transportadora>();
    st = con.createStatement();
    String sql = "SELECT codTransportadora, nome, cnpj, email, custokm FROM transportadora";
    ResultSet result = st.executeQuery(sql);
    while (result.next()) {
        list.add(
            new Transportadora(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getFloat(5)
            )
        );
    }

    return list;
}

public static String getNomeTransportadora(int codTransportadora, Connection con) throws SQLException{
    Statement st;
    st = con.createStatement();

    String sql = "SELECT nome FROM transportadora WHERE codtransportadora= " + codTransportadora;
    ResultSet result = st.executeQuery(sql);
    result.next();

    return result.getString(1);
}

    public static ArrayList listTransportadoraQuantidade(Connection con) throws SQLException{
        Statement st;
        ArrayList<TransportadoraQuantidade> list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT t.nome, COUNT(*) as numeroDeVezes " +
                        "FROM transportadora t JOIN venda v ON t.codtransportadora=v.codtransportadora " +
                        "GROUP BY t.codtransportadora " + 
                        "ORDER BY numeroDeVezes DESC";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new TransportadoraQuantidade(
                        result.getString(1),
                        result.getInt(2))  
                );
            }

            return list;
    }

    public static Transportadora getTransportadoraComMaiorPreco(Connection con) throws SQLException{
        Statement st;
        st = con.createStatement();

            String sql = "SELECT nome, custokm " + 
                         "FROM transportadora " + 
                         "WHERE custokm >= ALL (SELECT custokm FROM transportadora)";

            ResultSet result = st.executeQuery(sql);
            result.next();

            Transportadora t = new Transportadora(
                result.getString(1),
                result.getFloat(2)
            );
            return t;
    }

    public static ArrayList listTransportadorasUsadasParaProdutos(int codProduto, Connection con) throws SQLException{
        Statement st;
        ArrayList<TransportadoraQuantidade> list = new ArrayList();
        st = con.createStatement();


            String sql = "SELECT nome, COUNT(*) FROM transportadora " + 
                         "WHERE codtransportadora IN (SELECT codtransportadora FROM venda WHERE codproduto= " + codProduto + " ) " +
                         "GROUP BY nome";
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()) {
                list.add(
                    new TransportadoraQuantidade(
                        result.getString(1), 
                        result.getInt(2))
                );
            }

            return list;
    }

}
