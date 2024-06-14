package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import projeto.dados.Fornecedor;
import projeto.dados.FornecedorQuantidade;
import projeto.dados.CompraFornecedor;

public class FornecedorModel {

    public static void create(Fornecedor fornecedor, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Fornecedor ( nome, cnpj, email) VALUES (?,?,?)");
        st.setString(1, fornecedor.getNome());
        st.setString(2, fornecedor.getCnpj());
        st.setString(3, fornecedor.getEmail());
        st.execute();
        st.close();
    }

    public static ArrayList listAll(Connection con) throws SQLException {
        Statement st;
        ArrayList<Fornecedor> list = new ArrayList();
        st = con.createStatement();
        String sql = "SELECT codFornecedor, nome, cnpj, email FROM Fornecedor";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            list.add(new Fornecedor(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)));
        }
        return list;
    }

   
    public static String getNomeFornecedor(int codFornecedor, Connection con) throws SQLException{
        Statement st;
        HashSet<Fornecedor> list = new HashSet();

            st = con.createStatement();
            String sql = "SELECT nome FROM fornecedor WHERE codfornecedor= " + codFornecedor;
            ResultSet result = st.executeQuery(sql);
            result.next();

            return result.getString(1);
    }

    public static ArrayList listarComprasFornecedor(Connection con) throws SQLException {
        Statement st;
        ArrayList<CompraFornecedor> list = new ArrayList<>();
        st = con.createStatement();
        String sql = "SELECT c.codCompra, c.quantidade, f.nome " +
                     "FROM Compra c " +
                     "INNER JOIN Fornecedor f ON c.codFornecedor = f.codFornecedor";
        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            list.add(new CompraFornecedor(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3)));
        }
        return list;
    }
    public static  ArrayList listarFornecedorQuantidade(Connection con) throws SQLException {
        Statement st;
        ArrayList<FornecedorQuantidade> list = new ArrayList();
        st = con.createStatement();
        String sql = "SELECT f.nome, t.totalQuantidade " +
                     "FROM Fornecedor f " +
                     "INNER JOIN ( " +
                     "    SELECT codFornecedor, SUM(quantidade) AS totalQuantidade " +
                     "    FROM Compra c " +
                     "    GROUP BY codFornecedor " +
                     ") AS t ON f.codFornecedor = t.codFornecedor " +
                     "ORDER BY totalQuantidade DESC " +
                     "LIMIT 3";
        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            list.add(new FornecedorQuantidade(
                    result.getString(1),
                    result.getInt(2)));        }
        return list;
    }
}

