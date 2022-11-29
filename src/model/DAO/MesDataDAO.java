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
import model.bean.MesData;
import static view.Login.loginnome;

public class MesDataDAO {
    
    //public String bd=loginnome;
    String bd="karbonea_cofrinho_java";
    
    public List<MesData> read_distint_mes() {

        Connection con = DBconeta.getConnection(bd);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<MesData> mesesdata = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT DISTINCT MONTH(data), MONTHNAME(data) FROM "+loginnome+"_entradas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                MesData m = new MesData();

                m.setNomemes(rs.getString("monthname(data)"));
                m.setMes(rs.getInt("month(data)"));
                mesesdata.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MesDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBconeta.closeConnection(con, stmt, rs);
        }

        return mesesdata;

    }
    
}
