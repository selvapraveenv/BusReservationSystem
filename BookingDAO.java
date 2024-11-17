import java.util.Date;
import java.sql.*;
public class BookingDAO {

    public int getBookCount(int busNo, Date date) throws Exception{
        String query = "select count(passname) from booking where busno = ? and date = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        pst.setInt(1, busNo);
        pst.setDate(2, sqldate);
        
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1); 
    }

    public void addBooking(Booking curBooking) throws Exception{
        String query = "insert into booking values(?,?,?)";
        
        Connection con = DBConnect.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        java.sql.Date sqlDate = new java.sql.Date(curBooking.date.getTime());
        
        pst.setString(1, curBooking.passengerName);
        pst.setInt(2, curBooking.BusNo);
        pst.setDate(3, sqlDate);

        pst.executeUpdate();
    }
    
}
