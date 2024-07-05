/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {
        //conn = new conectaDAO().connectDB();
        conn = new conectaDAO().connectDB();

        String sql = "insert into produtos (nome, valor, status) VALUES (?,?,?)";

        try {
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            System.out.println("Produto cadastrado com sucesso");

            return true;

        } catch (SQLException ex) {
            System.out.println("Não foi possível cadastrar o produto");
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().connectDB();

        String sql = "select*from produtos";

        try {
            prep = this.conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));

                listaProdutos.add(p);
            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Não foi possível recuperar os dados do banco de dados");
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void atualizarStatus(int id) {

        conn = new conectaDAO().connectDB();

        String sql = "Update produtos set status = ? where id = ?";

        try {
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            prep.execute();
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a venda. Tente novamente ou mais tarde");
        }

    }
}
