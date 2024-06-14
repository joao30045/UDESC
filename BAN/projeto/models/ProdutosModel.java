package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import projeto.dados.Produtos;
import projeto.dados.ProdutosLucrativos;
import projeto.dados.ProdutosQuantidade;

public class ProdutosModel {

    public static void create(Produtos produto, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Produtos (precoUnitVenda, precoUnitCompra, descricao, quantidade, nome, datasheet) VALUES (?,?,?,?,?,?)");
        st.setFloat(1, produto.getPrecoUnitVenda());
        st.setFloat(2, produto.getPrecoUnitCompra());
        st.setString(3, produto.getDescricao());
        st.setInt(4, produto.getQuantidade());
        st.setString(5, produto.getNome());
        st.setString(6, produto.getDatasheet());
        
        st.execute();
        st.close();
    }

    public static ArrayList listAll(Connection con) throws SQLException {
        Statement st;
        ArrayList<Produtos> list = new ArrayList();
        st = con.createStatement();
        String sql = "SELECT codProduto, precoUnitVenda, precoUnitCompra, descricao, quantidade, nome, datasheet FROM produtos";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            list.add(new Produtos(
                    result.getInt(1),
                    result.getFloat(2),
                    result.getFloat(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getString(6),
                    result.getString(7)));
        }
        return list;
    }

    public static String getNomeProduto(int codProduto, Connection con) throws SQLException{
        Statement st;
        st = con.createStatement();

        String sql = "SELECT nome FROM produtos WHERE codproduto= " + codProduto;
        ResultSet result = st.executeQuery(sql);
        result.next();

        return result.getString(1);
    }

    public static ArrayList list10ProdutosMaisLucrativos(Connection con) throws SQLException{
        Statement st;
        ArrayList<ProdutosLucrativos> list = new ArrayList();

        st = con.createStatement();
        String sql = "SELECT p.nome, (SUM(v.quantidade) * (p.precounitvenda - p.precounitcompra)) as lucro " +
                      "FROM produtos p JOIN venda v ON p.codproduto=v.codproduto " + 
                      "GROUP BY p.codproduto " + 
                      "ORDER BY lucro DESC " +
                      "LIMIT 10";
        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            list.add(new ProdutosLucrativos(
                    result.getString(1), 
                    result.getFloat(2)
                   )
            );
        }

        return list;
    }

    public static ProdutosLucrativos produtoMaisBarato(Connection con) throws SQLException{
        Statement st;

        st = con.createStatement();
        String sql = "SELECT nome, precounitvenda FROM produtos WHERE " + 
                     "precounitvenda <= ALL (SELECT precounitvenda FROM produtos)";
        ResultSet result = st.executeQuery(sql);

        result.next();

        ProdutosLucrativos p = new ProdutosLucrativos(result.getString(1), 
                                                      result.getFloat(2)
                                                    );

       return p;
    }

    public static ArrayList listProdutosComPrecoAcimaMedia(Connection con) throws SQLException{
        Statement st;
        ArrayList<ProdutosLucrativos> list = new ArrayList();

        st = con.createStatement();
        String sql = "SELECT nome, precounitvenda FROM produtos WHERE " +
                     "precounitvenda > (SELECT AVG(precounitvenda) FROM produtos)";

        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            list.add(new ProdutosLucrativos(
                result.getString(1), 
                result.getFloat(2)));
        }

        return list;
    }
}
