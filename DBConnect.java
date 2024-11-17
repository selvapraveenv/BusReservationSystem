import java.sql.*;

public class DBConnect {
    public static final String url = "jdbc:mysql://localhost:3306/busres";
    public static final String name = "root";
    public static final String password = "selva9943";

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url, name, password);
    }
}
