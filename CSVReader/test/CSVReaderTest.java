import org.junit.Test;

import java.util.Locale;

public class CSVReaderTest {

    @Test
    public void basicReaderFunctionalityTest() {
        CSVReader reader;
        reader = new CSVReader("with-header.csv",",",true);
        while (reader.next()) {
            int id = reader.getInt("id");
            String name = reader.get("imie");
            String secondName = reader.get("nazwisko");
            String address = reader.get("ulica");
            int fare = reader.getInt("nrdomu");
            int flatNumber = reader.getInt("nrmieszkania");
            System.out.printf(Locale.US,"%d %s %s %s %d %d\n",id, name, secondName, address, fare, flatNumber);
        }
     }

    @Test
    public void testGet() {
    }

    @Test
    public void printCurrent() {
    }

    @Test
    public void getColumnLabels() {
    }

    @Test
    public void getRecordLength() {
    }

    @Test
    public void isMissing() {
    }

    @Test
    public void isMissing1() {
    }

    @Test
    public void get() {
    }

    @Test
    public void get1() {
    }

    @Test
    public void getInt() {
    }

    @Test
    public void getInt1() {
    }

    @Test
    public void getLong() {
    }

    @Test
    public void getLong1() {
    }

    @Test
    public void getDouble() {
    }

    @Test
    public void getDouble1() {
    }

    @Test
    public void main() {
    }
}