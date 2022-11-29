package model.DAO;

import DBconeta.DBconeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.User;

public class UserDAO {
   
    public void create(User newuser) {
        String bd="karbonea_cofrinho_java";

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        //Statement stmt1 = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM users WHERE nome = '"+ newuser.getNome()+"'");
            rs = stmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "ERRO: Já existe um Utilizador com o nome "+rs.getString("nome")); 
                
            } else {   
                int cria = JOptionPane.showConfirmDialog(null, "Deseja criar nova conta de utilizador?");
                System.out.println(cria);
                if (cria == 0){
                    JOptionPane.showMessageDialog(null, "A criar nova conta de utilizador...");
                                                
                try {
                    stmt = con.prepareStatement("INSERT INTO users (id, nome, pass, email)VALUES(default,?,?,?)");
                    stmt.setString(1, newuser.getNome());
                    stmt.setString(2, newuser.getPass());
                    stmt.setString(3, newuser.getEmail());

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "UTILIZADOR CRIADO");
                    
                    /*
                    //stmt1 = con.createStatement();
                    String sql = "CREATE DATABASE "+ newuser.getNome();
                    stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                    //stmt1.executeUpdate(sql);
                    
                    String sq2 = "USE "+ newuser.getNome();
                    stmt = con.prepareStatement(sq2);
                    stmt.executeUpdate();
                    */
                    stmt = con.prepareStatement("create table "+newuser.getNome()+"_tipoentrada (id int not null auto_increment, des_entrada varchar(20), primary key(id)) default charset = utf8");
                    stmt.executeUpdate();
                    stmt = con.prepareStatement("create table "+newuser.getNome()+"_entradas (id int not null auto_increment, tipo_ide int, data date, valor double, descr varchar(30), primary key(id), foreign key (tipo_ide) references tipoentrada(id)) default charset = utf8");
                    stmt.executeUpdate();
                    stmt = con.prepareStatement("create table "+newuser.getNome()+"_tiposaida (id int not null auto_increment, des_saida varchar(20), primary key(id)) default charset = utf8");
                    stmt.executeUpdate();
                    stmt = con.prepareStatement("create table "+newuser.getNome()+"_saidas (id int not null auto_increment, tipo_ids int, data date, valor double, descr varchar(30), primary key(id), foreign key (tipo_ids) references tiposaida(id)) default charset = utf8");
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "NOVA BASE DE DADOS CRIADA");
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    DBconeta.closeConnection(con, stmt, rs); 
                    
                } 
                
                } else { 
                    JOptionPane.showMessageDialog(null, "Operação anulada...");
                }
            }
        } catch (SQLException ex) {
                    System.out.println(ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }
    }

    public boolean checkLogin(String login, String senha) {
        String bd="karbonea_cofrinho_java";
        Connection con = DBconeta.getConnection(bd);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM users WHERE nome = ? and pass = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBconeta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return check;

    }


    
}
