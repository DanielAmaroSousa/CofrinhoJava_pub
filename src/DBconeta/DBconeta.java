package DBconeta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconeta {

    public static Connection getConnection(String basedados) {
        
        Connection coneta = null;
        
        
        try {
            String driverName = "com.mysql.jdbc.Driver";                        
            Class.forName(driverName);
                

            String mydatabase = basedados;        //nome do seu banco de dados
            String username = ""/*removed*/;        //nome de um usuário de seu BD      
            String password = ""/*removed*/;      //sua senha de acesso
            String url ="jdbc:mysql://"/*removed*/":3306/"+mydatabase;
            coneta = DriverManager.getConnection(url, username, password);

            
            
            
        } catch (ClassNotFoundException | SQLException ex) {

            throw new RuntimeException("Erro na conexão: ", ex);

        } return coneta;
    } 

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBconeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBconeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
