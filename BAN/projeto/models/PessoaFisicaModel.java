package projeto.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.plaf.nimbus.State;

import projeto.dados.PessoaFisica;
import projeto.dados.TrimestresConstantes;

public class PessoaFisicaModel {

    
    public static void create(PessoaFisica p, Connection con) throws SQLException{
        PreparedStatement st;

            st = con.prepareStatement("INSERT INTO clientes (tipo, nome, datanascimento, sexo, email, telefone, rua, bairro, cep, cpf)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
            st.setInt(1, 1);
            st.setString(2, p.getNome());
            st.setDate(3, p.getDataNacimento());
            st.setString(4, p.getSexo());
            st.setString(5, p.getEmail());
            st.setString(6, p.getTelefone());
            st.setString(7, p.getRua());
            st.setString(8, p.getBairro());
            st.setInt(9, p.getCep());
            st.setString(10, p.getCpf());
            st.execute();
            st.close();
    } 

    

    public static ArrayList listAll(Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT codCliente, nome, datanascimento, sexo, email, telefone, rua, bairro, cep, cpf FROM clientes WHERE tipo=1";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new PessoaFisica(
                        result.getInt(1), 
                        1, 
                        result.getString(2), 
                        result.getDate(3), 
                        result.getString(4), 
                        result.getString(5), 
                        result.getString(6), 
                        result.getString(7), 
                        result.getString(8),
                        result.getInt(9), 
                        result.getString(10)
                    )
                );
            }

        return list;
    }

    public static ArrayList listPessoasFisicasCompraramProdutosTrimestre(int tri, int ano, Connection con) throws SQLException{
        Statement st;
        ArrayList list = new ArrayList();

            st = con.createStatement();
            String sql = "SELECT DISTINCT c.codCliente, c.nome, c.datanascimento, c.sexo, c.email, c.telefone, c.rua, c.bairro, c.cep, c.cpf " +
                         "FROM clientes c JOIN venda v ON c.codcliente=v.codcliente WHERE tipo=1 AND  DATE_PART('MONTH', v.data) >= " + 
                         tri + " AND DATE_PART('MONTH', v.data) <= " + (tri+2) +
                         " AND DATE_PART('YEAR', v.data) = " + ano;

            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                list.add(
                    new PessoaFisica(
                        result.getInt(1), 
                        1, 
                        result.getString(2), 
                        result.getDate(3), 
                        result.getString(4), 
                        result.getString(5), 
                        result.getString(6), 
                        result.getString(7), 
                        result.getString(8),
                        result.getInt(9), 
                        result.getString(10)
                    )
                );
            }
          
            return list;
    }

    public static int numPessoasFisicasCompraramTodosOsProdutos(Connection con) throws SQLException{
        Statement st;

            st = con.createStatement();
            String sql = "SELECT COUNT(*) FROM clientes c WHERE " +
                             "NOT EXISTS (SELECT * FROM produtos p WHERE " +
                                 "NOT EXISTS (SELECT * FROM venda v WHERE " +
                                       "c.codcliente=v.codcliente AND p.codproduto=v.codproduto)) " +
                             "AND c.tipo=1";
            
            ResultSet result = st.executeQuery(sql);
            result.next();

            return result.getInt(1);
            
    }
}
