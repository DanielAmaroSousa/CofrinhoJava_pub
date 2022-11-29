package model.DAO;

import DBconeta.DBconeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.TipoEntrada;
import static view.Login.loginnome;

public class TipoEntradaDAO {

    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    
    public void create(TipoEntrada te) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO "+loginnome+"_tipoentrada (id, des_entrada)VALUES(default,?)");
            stmt.setString(1, te.getDes_entrada());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }
    }

    public List<TipoEntrada> read() {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<TipoEntrada> tipoentradalist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM "+loginnome+"_tipoentrada");
            rs = stmt.executeQuery();

            while (rs.next()) {

                TipoEntrada ter = new TipoEntrada();

                ter.setId(rs.getInt("id"));
                ter.setDes_entrada(rs.getString("des_entrada"));

                tipoentradalist.add(ter);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TipoEntradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return tipoentradalist;

    }
    
    public void delete(TipoEntrada te) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM "+loginnome+"_tipoentrada WHERE id = ?");
            stmt.setInt(1, te.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }

    }
    
}
