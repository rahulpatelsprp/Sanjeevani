package sanjeevaniapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.ReceptionistPojo;

public class ReceptionistDao {

    public static void updateName(String currName, String newName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update receptionists set receptionist_name=? where receptionist_name=?");
        ps.setString(1, newName);
        ps.setString(2, currName);
        ps.executeUpdate();
    }

    public static boolean addReceptionist(ReceptionistPojo rec) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into receptionists values(?,?,?)");
        ps.setString(1, rec.getReceptionistId());
        ps.setString(2, rec.getReceptionistName());
        ps.setString(3, rec.getGender());
        return ps.executeUpdate() == 1;
    }

    public static String getNewRecId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(receptionist_id) from receptionists");
        rs.next();
        int recId = 101;
        String id = rs.getString(1);
        if (id != null) {
            String num = id.substring(3);
            recId = Integer.parseInt(num) + 1;
        }
        return "REC" + recId;
    }
      public static List<ReceptionistPojo> getAllReceptionistDetails() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from receptionists order by receptionist_id");
        List<ReceptionistPojo> recList = new ArrayList<>();
        while (rs.next()) {
            ReceptionistPojo rec = new ReceptionistPojo();
            rec.setReceptionistId(rs.getString(1));
            rec.setReceptionistName(rs.getString(2));
            rec.setGender(rs.getString(3));
           recList.add(rec);
        }
        return recList;
    }
       public static List<String> getAllReceptionistId() throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery("Select receptionist_id from receptionists");
        List<String> recepList=new ArrayList<>();
        while(rs.next())
        {
        recepList.add(rs.getString(1));
        }
        return recepList;
}
        public static boolean deleteReceptionistById(String recepId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select receptionist_name from receptionists where receptionist_id=?");
        ps.setString(1, recepId);
        ResultSet rs =ps.executeQuery();
        rs.next();
        String recepName=rs.getString(1);
        UserDao.deleteByUserName(recepName);
        ps=conn.prepareStatement("delete from receptionists where receptionist_id=?");
        ps.setString(1, recepId);
        return ps.executeUpdate()==1;
     }
        public static  String getReceptionistNameById(String recepId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select receptionist_name from receptionists where receptionist_id=?");
        ps.setString(1, recepId);
        ResultSet rs =ps.executeQuery();
        rs.next();
        return rs.getString(1);
       }
         public static boolean updateGender(String recepId, String gender) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update receptionists set gender=? where receptionist_id=?");
        ps.setString(1, gender);
        ps.setString(2, recepId);
        return ps.executeUpdate()==1;
    }
}
