package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import projeto.dados.Venda;
import projeto.dados.FormaPagamento;

public class VendaModel {
    public static void create(Venda v, Connection con) throws SQLException{
        PreparedStatement st;

        st = con.prepareStatement("INSERT INTO venda (codcliente, codproduto, quantidade, formapagamento, codtransportadora, data)"
                                   + "VALUES(?, ?, ?, ?, ?, ?)"
                                );
    
            st.setInt(1, v.getCodCliente());
            st.setInt(2, v.getCodProduto());
            st.setInt(3, v.getQuantidade());
            st.setString(4, v.getFormaPagamento());
            st.setInt(5, v.getCodTransportadora());
            st.setDate(6, v.getData());
            st.execute();
            st.close();
    }

    public static ArrayList listAll(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT codvenda, codcliente, codproduto, quantidade, formapagamento, codtransportadora, data FROM venda";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new Venda(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getDate(7)
                    )
                );
            }

            return list;
    }

    public static ArrayList listFormaPagamento(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT formapagamento, COUNT(*) " +
                          "FROM clientes c JOIN venda v ON c.codcliente = v.codcliente " +
                          "GROUP BY formapagamento";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new FormaPagamento(
                        result.getString(1), 
                        result.getInt(2))
                );
            }

            return list;
    }

    public static ArrayList listVendasClientesPorTipo(int tipo, Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT codvenda, codcliente, codproduto, quantidade, formapagamento, codtransportadora, data " +
                         "FROM venda WHERE " + 
                         "codcliente IN (SELECT codcliente FROM clientes WHERE tipo= " + tipo + " )";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new Venda(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getDate(7)
                    )
                );
            }

            return list;
    }

    public static ArrayList list(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

         st = con.createStatement();
         String sql = "SELECT codvenda, codcliente, codproduto, quantidade, formapagamento, codtransportadora, data " +
                      "FROM venda WHERE "+
                      "codtransportadora IN (SELECT codtransportadora FROM transportadora  WHERE " +
					  "custokm = (SELECT MIN(custokm) FROM transportadora))"; 
        
        ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new Venda(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getDate(7)
                    )
                );
            }

        return list;
    }

    public static ArrayList listVendasComTransportadoraMaisBarata(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

         st = con.createStatement();
         String sql = "SELECT codvenda, codcliente, codproduto, quantidade, formapagamento, codtransportadora, data " +
                      "FROM venda WHERE "+
                      "codtransportadora IN (SELECT codtransportadora FROM transportadora  WHERE " +
					  "custokm = (SELECT MIN(custokm) FROM transportadora))"; 
        
        ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new Venda(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getDate(7)
                    )
                );
            }

        return list;
    }
}
