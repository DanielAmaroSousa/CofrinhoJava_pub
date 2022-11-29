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
import model.bean.Saida;
import static view.Login.loginnome;

public class SaidaDAO {

    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    
    public void create(Saida e) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO "+loginnome+"_saidas (id, tipo_ids, data, valor, descr)VALUES(default,?,?,?,?)");
            //stmt.setInt(1, e.getId());
            stmt.setInt(1, e.getTipo_ids());
            stmt.setString(2, e.getData());
            stmt.setDouble(3, e.getValor());
            stmt.setString(4, e.getDescr());
            

            stmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }

    }

    public List<Saida> read() {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Saida> saidas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM "+loginnome+"_saidas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Saida saida = new Saida();

                saida.setId(rs.getInt("id"));
                saida.setTipo_ids(rs.getInt("tipo_ids"));
                saida.setData(rs.getString("data"));
                saida.setValor(rs.getDouble("valor"));
                saida.setDescr(rs.getString("descr"));
                saidas.add(saida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return saidas;

    }
    
    public List<Saida> read_testr(String idts) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Saida> saidas = new ArrayList<>();

        try {
            if (!idts.equals("*")) {
                stmt = con.prepareStatement("select * from "+loginnome+"_saidas e1 join "+loginnome+"_tiposaida t1 on e1.tipo_ids = t1.id where tipo_ids = ?");
                stmt.setString(1, idts);
            } else { 
                stmt = con.prepareStatement("select * from "+loginnome+"_saidas e1 join "+loginnome+"_tiposaida t1 on e1.tipo_ids = t1.id");
            }
            stmt = con.prepareStatement("select * from "+loginnome+"_saidas e1 join "+loginnome+"_tiposaida t1 on e1.tipo_ids = t1.id;");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Saida saida = new Saida();

                saida.setId(rs.getInt("id"));
                saida.setDes_saida(rs.getString("des_saida"));
                saida.setData(rs.getString("data"));
                saida.setValor(rs.getDouble("valor"));
                saida.setDescr(rs.getString("descr"));
                saidas.add(saida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return saidas;

    }
        
    public void delete(Saida e) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM "+loginnome+"_saidas WHERE id = ?");
            stmt.setInt(1, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }

    }
    
      public List<Saida> read_edata(String procuradata1, String procuradata2) {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Saida> saidas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from "+loginnome+"_saidas s1 join "+loginnome+"_tiposaida t1 on s1.tipo_ids = t1.id where data >= ? && data < ?");
            stmt.setString(1, procuradata1);
            stmt.setString(2, procuradata2);
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Saida saida = new Saida();

                saida.setId(rs.getInt("id"));
                saida.setDes_saida(rs.getString("des_saida"));
                saida.setData(rs.getString("data"));
                saida.setValor(rs.getDouble("valor"));
                saida.setDescr(rs.getString("descr"));
                saidas.add(saida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return saidas;

    }
    
    public List<Saida> read_pesquisa(String procuradata1, String procuradata2, String idte) {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Saida> saidas = new ArrayList<>();

        try {
             if (!idte.equals("*")) {
                stmt = con.prepareStatement("select * from "+loginnome+"_saidas e1 join "+loginnome+"_tiposaida t1 on e1.tipo_ids = t1.id where data >= ? && data < ? && tipo_ids = ?");
                stmt.setString(1, procuradata1);
                stmt.setString(2, procuradata2);
                stmt.setString(3, idte);
            } else { 
                stmt = con.prepareStatement("select * from "+loginnome+"_saidas e1 join "+loginnome+"_tiposaida t1 on e1.tipo_ids = t1.id where data >= ? && data < ?");
                stmt.setString(1, procuradata1);
                stmt.setString(2, procuradata2);
            }
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Saida saida = new Saida();

                saida.setId(rs.getInt("id"));
                saida.setDes_saida(rs.getString("des_saida"));
                saida.setData(rs.getString("data"));
                saida.setValor(rs.getDouble("valor"));
                saida.setDescr(rs.getString("descr"));
                saidas.add(saida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return saidas;

    }
}
