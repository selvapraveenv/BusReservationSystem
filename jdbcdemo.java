import java.sql.*;

public class jdbcdemo {
    public static void main(String[] args) throws Exception {
        batch();
    }
    public static void readData() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "selva9943";
        String query = "select * from emp";
        
        Connection con = DriverManager.getConnection(url,username,password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            System.out.println("Id:"+ rs.getInt(1));
            System.out.println("Name:" + rs.getString(2));
            System.out.println("Sal:" + rs.getInt(3));
        }
        con.close();
    }

    public static void insertData() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "selva9943";
        String query = "insert into emp values(?,?,?)";
        int id = 5;
        String name = "virat";
        int sal = 100000;
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        st.setString(2, name);
        st.setInt(3, sal);

        int rs = st.executeUpdate();

        System.out.println("Affected rows:"+ rs);
        con.close();
    }

    public static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "selva9943";
        int id = 5;
        String query = "delete from emp where id=" + id;

        Connection con = DriverManager.getConnection(url,username,password);
        Statement st = con.createStatement();
        
        int rows = st.executeUpdate(query);

        System.out.println("Affected rows:"+ rows);
        con.close();
    }

    public static void update() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "selva9943";

        String query = "update emp set sal = ? where id = ?;";
        int sal = 150000;
        int id = 1;
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, sal);
        st.setInt(2, id);
        int rows = st.executeUpdate();

        System.out.println("Affected rows:"+ rows);
        con.close();
    }

    public static void procedureCalling() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String name = "root";
        String password = "selva9943";

        Connection con = DriverManager.getConnection(url, name, password);
        CallableStatement cst = con.prepareCall("{call getEmp()}");
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            System.out.println("Id: "+rs.getInt(1));
            System.out.println("Name: "+rs.getString(2));
            System.out.println("Salary: "+rs.getInt(3));
            System.out.println();
        }
    }
    
    public static void procedureCalling2() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String name = "root";
        String password = "selva9943";

        Connection con = DriverManager.getConnection(url, name, password);
        CallableStatement cst = con.prepareCall("{call getEmpById(?)}");
        
        int id = 2;
        cst.setInt(1, id);
        ResultSet rs = cst.executeQuery();
        while (rs.next()) {
            System.out.println("Id: "+rs.getInt(1));
            System.out.println("Name: "+rs.getString(2));
            System.out.println("Salary: "+rs.getInt(3));
            System.out.println();
        }
        con.close();
    }

    public static void procedureCalling3() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String name = "root";
        String password = "selva9943";

        Connection con = DriverManager.getConnection(url, name, password);
        CallableStatement cst = con.prepareCall("{call getNameById(?,?)}");
        
        int id = 2;
        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);

        int rs = cst.executeUpdate();
        System.out.println(rs +" " + cst.getString(2));
        
        con.close();
    }

    public static void commit() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String name = "root";
        String password = "selva9943";

        String query1 = "update emp set sal = 400000 where id = 3";
        String query2 = "update emp set sal = 400000 where id = 4";
        Connection con = DriverManager.getConnection(url, name, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int rows1 = st.executeUpdate(query1);
        System.out.println(rows1);
        int rows2 = st.executeUpdate(query2);
        System.out.println(rows2);

        if(rows1>0 && rows2>0)
            con.commit();

        con.close();
    }

    public static void batch() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String name = "root";
        String password = "selva9943";

        String query1 = "update emp set sal = 500000 where id = 3";
        String query2 = "update emp set sal = 500000 where id = 4";
        Connection con = DriverManager.getConnection(url, name, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);

        int[] res = st.executeBatch();

        for(int i:res){
            if(i<=0)
                con.rollback();
        }
        con.commit();
        con.close();
    }


}