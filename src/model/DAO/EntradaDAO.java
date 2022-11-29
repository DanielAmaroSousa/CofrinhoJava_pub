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
import model.bean.Entrada;
import static view.Login.loginnome;

public class EntradaDAO {

    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    
    
    public void create(Entrada e) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO "+loginnome+"_entradas (id, tipo_ide, data, valor, descr)VALUES(default,?,?,?,?)");
            //stmt.setInt(1, e.getId());
            stmt.setInt(1, e.getTipo_ide());
            stmt.setString(2, e.getData());
            stmt.setDouble(3, e.getValor());
            stmt.setString(4, e.getDescr());
            
            stmt.executeUpdate();

        //    JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }

    }

    public List<Entrada> read() {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> entradas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM "+loginnome+"_entradas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada entrada = new Entrada();

                entrada.setId(rs.getInt("id"));
                entrada.setTipo_ide(rs.getInt("tipo_ide"));
                entrada.setData(rs.getString("data"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDescr(rs.getString("descr"));
                entradas.add(entrada);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return entradas;

    }
    
    public List<Entrada> read_testr(String idte) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> entradas = new ArrayList<>();

        try {
            if (!idte.equals("*")) {
                stmt = con.prepareStatement("select * from "+loginnome+"_entradas e1 join "+loginnome+"_tipoentrada t1 on e1.tipo_ide = t1.id where tipo_ide = ?");
                stmt.setString(1, idte);
            } else { 
                stmt = con.prepareStatement("select * from "+loginnome+"_entradas e1 join "+loginnome+"_tipoentrada t1 on e1.tipo_ide = t1.id");
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada entrada = new Entrada();

                entrada.setId(rs.getInt("id"));
                entrada.setDes_entrada(rs.getString("des_entrada"));
                //entrada.setTipo_ide(rs.getInt("tipo_ide"));
                entrada.setData(rs.getString("data"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDescr(rs.getString("descr"));
                entradas.add(entrada);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return entradas;

    }
     
           
    public void delete(Entrada e) {
        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM "+loginnome+"_entradas WHERE id = ?");
            stmt.setInt(1, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            DBconeta.closeConnection(con, stmt);
        }

    }
    
    public List<Entrada> read_edata(String procuradata1, String procuradata2) {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> entradas = new ArrayList<>();

        try {
 
            stmt = con.prepareStatement("select * from "+loginnome+"_entradas e1 join "+loginnome+"_tipoentrada t1 on e1.tipo_ide = t1.id where data >= ? && data < ?");
            stmt.setString(1, procuradata1);
            stmt.setString(2, procuradata2);
                      
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada entrada = new Entrada();

                entrada.setId(rs.getInt("id"));
                entrada.setDes_entrada(rs.getString("des_entrada"));
                entrada.setData(rs.getString("data"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDescr(rs.getString("descr"));
                entradas.add(entrada);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }
        
        return entradas;
    }
        
    public List<Entrada> read_pesquisa(String procuradata1, String procuradata2, String idte) {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> entradas = new ArrayList<>();

        try {
             if (!idte.equals("*")) {
                stmt = con.prepareStatement("select * from "+loginnome+"_entradas e1 join "+loginnome+"_tipoentrada t1 on e1.tipo_ide = t1.id where data >= ? && data < ? && tipo_ide = ?");
                stmt.setString(1, procuradata1);
                stmt.setString(2, procuradata2);
                stmt.setString(3, idte);
            } else { 
                stmt = con.prepareStatement("select * from "+loginnome+"_entradas e1 join "+loginnome+"_tipoentrada t1 on e1.tipo_ide = t1.id where data >= ? && data < ?");
                stmt.setString(1, procuradata1);
                stmt.setString(2, procuradata2);
            }
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada entrada = new Entrada();

                entrada.setId(rs.getInt("id"));
                entrada.setDes_entrada(rs.getString("des_entrada"));
                entrada.setData(rs.getString("data"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDescr(rs.getString("descr"));
                entradas.add(entrada);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return entradas;

    }
    
}
