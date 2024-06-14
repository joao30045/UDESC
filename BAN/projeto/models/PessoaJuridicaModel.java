package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import projeto.dados.PessoaJuridica;
import projeto.dados.PrincipaisPessoasJuridicas;

public class PessoaJuridicaModel {
    public static void create(PessoaJuridica p, Connection con) throws SQLException{
        PreparedStatement st;

        st = con.prepareStatement("INSERT INTO clientes (tipo, nome, email, telefone, rua, bairro, cep, cnpj)" +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            st.setInt(1, 2);
            st.setString(2, p.getNome());
            st.setString(3, p.getEmail());
            st.setString(4, p.getTelefone());
            st.setString(5, p.getRua());
            st.setString(6, p.getBairro());
            st.setInt(7, p.getCep());
            st.setString(8, p.getCnpj());
            st.execute();
            st.close();
    }

    public static ArrayList listAll(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT codCliente, nome, email, telefone, rua, bairro, cep, cnpj FROM clientes WHERE tipo=2";
            ResultSet result = st.executeQuery(sql);

            while (result.next()){
                list.add(
                    new PessoaJuridica(
                         result.getInt(1),
                         2, 
                         result.getString(2), 
                         result.getString(3), 
                         result.getString(4), 
                         result.getString(5), 
                         result.getString(6),
                         result.getInt(7), 
                         result.getString(8)
                    )
                ); 

            }

            return list;
    }

    public static ArrayList listPessoasJuridicasPrincipais(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT c.codCliente, c.nome, c.email, c.telefone, c.rua, c.bairro, c.cep, c.cnpj, " + 
                         "COUNT(*) as qtdProdutosVendidos, SUM(v.quantidade * p.precounitvenda) as valor " +
                         "FROM clientes c JOIN venda v ON c.codcliente=v.codcliente " + 
                         "JOIN produtos p ON p.codproduto=v.codproduto " +
                         "WHERE c.tipo = 2 " +
                         "GROUP BY c.codCliente, c.nome, c.email, c.telefone, c.rua, c.bairro, c.cep, c.cnpj " +
                         "ORDER BY valor DESC, qtdProdutosVendidos";

            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new PrincipaisPessoasJuridicas(
                        new PessoaJuridica(
                                result.getInt(1),
                                2, 
                                result.getString(2), 
                                result.getString(3), 
                                result.getString(4), 
                                result.getString(5), 
                                result.getString(6),
                                result.getInt(7), 
                                result.getString(8)
                        ),
                        result.getInt(9),
                        result.getFloat(10)
                    )
                );
            }

        return list;
    }

    public static ArrayList listPessoasJuridicasCompraramMenosTrimestre(int tri, int ano, Connection con) throws SQLException{
        int tri_anterior;
        int tri_anterior_ano;

        if(tri != 1){
            tri_anterior = tri - 3;
            tri_anterior_ano = ano;
        }else{
            tri_anterior = 10;
            tri_anterior_ano = ano - 1;
        }
      

        Statement st;
        ArrayList list = new ArrayList<>();

            st = con.createStatement();
            String sql = "SELECT c.codCliente, c.nome, c.email, c.telefone, c.rua, c.bairro, c.cep, c.cnpj FROM clientes c " +
                         "WHERE tipo=2 " + 
                         "AND (SELECT SUM(quantidade) FROM venda " +
	                     "WHERE c.codcliente=codcliente AND DATE_PART('MONTH', data)>=" + tri_anterior + " AND DATE_PART('MONTH', data)<= " + (tri_anterior+2) + " AND DATE_PART('YEAR', data) =" + tri_anterior_ano + " ) " +
	                     "> " + 
	                     "(SELECT SUM(quantidade) FROM venda  " +
	                     "WHERE c.codcliente=codcliente AND DATE_PART('MONTH', data)>=" + tri + " AND DATE_PART('MONTH', data)<= " + (tri+2) + " AND DATE_PART('YEAR', data) =" + ano + " )";

            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                     new PessoaJuridica(
                         result.getInt(1),
                         2, 
                         result.getString(2), 
                         result.getString(3), 
                         result.getString(4), 
                         result.getString(5), 
                         result.getString(6),
                         result.getInt(7), 
                         result.getString(8)
                    )
                );
            }

            return list;
    }

}
