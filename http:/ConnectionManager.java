package programs;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection c = null;
    PreparedStatement stmt = null;
    private static String Driver = null;
    private static String url = null;
    private static String UserName = null;
    private static String Password = null;

    public static Connection getConnection() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/Demo.properties"));

            Driver = props.getProperty("Driver");
            url = props.getProperty("connectionURL");
            UserName = props.getProperty("UserName");
            Password = props.getProperty("Password");
            Class.forName(Driver);
            c = DriverManager.getConnection(url, UserName, Password);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return c;
    }
}
