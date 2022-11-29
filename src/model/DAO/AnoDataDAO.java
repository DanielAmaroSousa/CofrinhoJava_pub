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
import model.bean.AnoData;
import static view.Login.loginnome;

public class AnoDataDAO {
           
    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    
    public List<AnoData> read_distint_ano() {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<AnoData> anosdata = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT DISTINCT YEAR(data) FROM "+loginnome+"_entradas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                AnoData a = new AnoData();

                a.setAno(rs.getInt("year(data)"));
                anosdata.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AnoDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return anosdata;

    }
}
