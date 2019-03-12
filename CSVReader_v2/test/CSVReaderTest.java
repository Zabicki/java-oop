import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class CSVReaderTest {
    private CSVReader reader;

    @Test
    /*
    checks if no exception is thrown
     */
    public void readWholeMissingValuesFile() {
        reader = new CSVReader("missing-values.csv",";",true);
        reader.printHeader();
        while(reader.next()) {
            int id = reader.getInt("id");
            int parent = reader.getInt("parent");
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            long population = reader.getLong("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");

            System.out.printf(Locale.US,"%d %d %s %d %d %f %f\n",id, parent, name, admin_level, population, area, density);
        }
    }

    @Test
    /*
    checks if no exception is thrown
     */
    public void readhWholeWithHeaderFile() {
        reader = new CSVReader("with-header.csv",";",true);
        reader.printHeader();
        while(reader.next()) {
            int id = reader.getInt("id");
            String imie = reader.get("imie");
            String nazwisko = reader.get("nazwisko");
            String ulica = reader.get("ulica");
            int nrdomu = reader.getInt("nrdomu");
            int nrmieszkania = reader.getInt("nrmieszkania");

            System.out.printf(Locale.US,"%d %s %s %s %d %d\n",id, imie, nazwisko, ulica, nrdomu, nrmieszkania);
        }
    }

    @Test
    /*
    checks if no exception is thrown
     */
    public void readWholeNoHeaderFile() {
        reader = new CSVReader("no-header.csv",";",false);
        while(reader.next()) {
            int id = reader.getInt(0);
            String imie = reader.get(1);
            String nazwisko = reader.get(2);
            String ulica = reader.get(3);
            int nrdomu = reader.getInt(4);
            int nrmieszkania = reader.getInt(5);

            System.out.printf(Locale.US,"%d %s %s %s %d %d\n",id, imie, nazwisko, ulica, nrdomu, nrmieszkania);
        }
    }

    @Test
    /*
    checks if no exception is thrown
     */
    public void readWholeAcceleratorFile() {
        reader = new CSVReader("accelerator.csv",";",true);
        while(reader.next()) {
            double x = reader.getDouble("X");
            double y = reader.getDouble("Y");
            double z = reader.getDouble("Z");
            double longitude = reader.getDouble("longitude");
            double latitude = reader.getDouble("latitude");
            double speed = reader.getDouble("speed");
            double time = reader.getDouble("time");
            String label = reader.get("label");

            System.out.printf(Locale.US,"%f %f %f %f %f %f %f %s\n", x, y, z, longitude, latitude, speed, time, label);
        }
    }

    @Test
    /*
    checks if no exception is thrown
     */
    public void readWholeElecFile() {
        reader = new CSVReader("elec.csv",",",true);
        while(reader.next()) {
            int date = reader.getInt("date");
            int day = reader.getInt("day");
            double period = reader.getDouble("period");
            double nswprice = reader.getDouble("nswprice");
            double nswdemand = reader.getDouble("nswdemand");
            double vicprice = reader.getDouble("vicprice");
            double vicdemand = reader.getDouble("vicdemand");
            double transfer = reader.getDouble("transfer");
            String aClass = reader.get("class");

            System.out.printf(Locale.US,"%d %d %f %f %f %f %f %f %s\n", date, day, period, nswprice, nswdemand,
                    vicprice, vicdemand, transfer, aClass);
        }
    }

    /*
    test-file.csv has missing, int, long, double and string values, is a good base to test the reader functionality
    Content:
    id;parent;name;admin_level;population;area;density
    11670;11649;gmina Lanckorona;7;6165;40.4298;152.487
    11672;11670;Lanckorona;8;;11.7616;
    10467;10465;Kolonia Wschodnia;11;;2.06667;
    10468;10465;Kolonia Zachodnia;11;;1.96228;
    10469;10464;Bêdkowice;8;;5.99101;;
     */

    @Before
    public void initialize() {
        reader = new CSVReader("test-file.csv",";",true);

    }

    @Test
    public void parseHeader() {
        //parseHeader() was called in constructor called in initialize() method,
        //so there's no need to call it again, it can be directly tested.
        assertEquals("id;parent;name;admin_level;population;area;density",
                reader.getHeaderString());
    }

    @Test
    public void getRecordLength() {
        reader.next();
        assertEquals(7,reader.getRecordLength());
    }

    @Test
    public void getWithIndex() {
        //check id, name, population
        reader.next(); //get first line
        assertEquals("gmina Lanckorona", reader.get(2));
        reader.next();
        assertEquals("Lanckorona", reader.get(2));
        assertEquals("",reader.get(4));
    }

    /*
    @Test
    public void next() {
    }

    @Test
    public void isMissingWithIndex() {
    }

    @Test
    public void isMissingWithLabel() {
    }

    @Test
    public void getWithLabel() {
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
    public void printHeader() {
    }

    @Test
    public void printCurrent() {
    }*/
}