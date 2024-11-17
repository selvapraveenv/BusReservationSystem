import java.sql.*;

public class BusDAO {
    public void displayBusInfo() throws Exception{
        String query = "select * from bus";
        Connection con = DBConnect.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            System.out.println("Bus No:"+rs.getInt(1));
            int ac = rs.getInt(2);
            if(ac==1){
                System.out.println("Ac:Yes");
            }
            else{
                System.out.println("Ac:No");
            }            
            System.out.println("Capacity:"+rs.getInt(3));
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    public int getCapacity(int id) throws Exception{
        String query = "select capacity from bus where id = " + id;
        Connection con = DBConnect.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();

        return rs.getInt(1);
    }
}