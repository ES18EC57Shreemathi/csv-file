import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import java.util.Properties;

import com.csvreader.CsvReader;

public class CSVToDB {

    void retrieveData(CsvReader products) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {

            c = ConnectionManager.getConnection();
            System.out.println(products.readHeaders());

            System.out.println(products.getHeaderCount());
            String a;

            stmt = c.prepareStatement("insert into csvtodb values(?,?,?)");
            final int batchSize = 1000;
            int count = 0;
        while (products.readRecord()) {

                stmt.setString(1, products.get("Name"));
                stmt.setString(2, products.get("Address"));
                stmt.setString(3, products.get("City"));
                stmt.addBatch();
                if (++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }

            stmt.executeBatch();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        } finally {
            try {

                products.close();
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                c.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Records created successfully");

    }
}
