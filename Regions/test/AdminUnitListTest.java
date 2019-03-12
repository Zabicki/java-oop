import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class AdminUnitListTest {

    @Test
    public void listFirst10Elements() {
        AdminUnitList test = new AdminUnitList();
        test.read("admin-units.csv");
        test.list(System.out,0,10);
    }

    @Test
    public void list() {
    }

    @Test
    public void list1() {
    }
}