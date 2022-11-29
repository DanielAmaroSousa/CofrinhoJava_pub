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
import model.bean.TipoSaida;
import static view.Login.loginnome;

public class TipoSaidaDAO {
    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    public void create(TipoSaida te) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO "+loginnome+"_tiposaida (id, des_saida)VALUES(default,?)");
            stmt.setString(1, te.getDes_saida());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }
    }

    public List<TipoSaida> read() {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<TipoSaida> tiposaidalist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM "+loginnome+"_tiposaida");
            rs = stmt.executeQuery();

            while (rs.next()) {

                TipoSaida ter = new TipoSaida();

                ter.setId(rs.getInt("id"));
                ter.setDes_saida(rs.getString("des_saida"));

                tiposaidalist.add(ter);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TipoSaidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return tiposaidalist;

    }
    
    public void delete(TipoSaida te) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM "+loginnome+"_tiposaida WHERE id = ?");
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
