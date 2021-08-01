package programs;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVLoader {
    static CSVToDB cd   = new CSVToDB();

    public CSVLoader(char seprator) {
        super();
        this.seprator = seprator;
    }

    public void loadCSV(String fileName) {
        CsvReader products = null;
        try {
            products = new CsvReader(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cd.retrieveData(products);
        products.close();
    }


}
