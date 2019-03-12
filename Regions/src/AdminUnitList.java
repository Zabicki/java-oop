import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    CSVReader reader;
    HashMap<Long, AdminUnit> longToAdminUnit = new HashMap<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    /**
     * Function creates new CSVReader type object, reads data from the file line by line,
     * with every iteration creates new AdminUnit object, gives needed parameter to it's constructor,
     * adds new adminUnit to 'units' list,
     * in the end it sets parent for each adminUnit
     * @param filename name of the database file
     */
    public void read(String filename) {
        reader = new CSVReader(filename,",",true);
        AdminUnit tmp;
        int i = 1;

        while (reader.next()) {
            //determine xmin,ymin,xmax,ymax
            List<Double> xlist = new ArrayList<>();
            xlist.add(reader.getDouble("x1"));
            xlist.add(reader.getDouble("x2"));
            xlist.add(reader.getDouble("x3"));
            xlist.add(reader.getDouble("x4"));
            List<Double> ylist = new ArrayList<>();
            ylist.add(reader.getDouble("x1"));
            ylist.add(reader.getDouble("x2"));
            ylist.add(reader.getDouble("x3"));
            ylist.add(reader.getDouble("x4"));
            double xmax = xlist.get(0);
            double ymax = ylist.get(0);
            double xmin = xlist.get(0);
            double ymin = ylist.get(0);
            for (double d : xlist) {
                xmax = d > xmax ? d : xmax;
                xmin = d < xmin ? d : xmin;
            }
            for (double d : ylist) {
                ymax = d > ymax ? d : ymax;
                ymin = d < ymin ? d : ymin;
            }

            tmp = new AdminUnit(reader.getLong("parent"),
                                reader.get("name"),
                                reader.getInt("admin_level"),
                                reader.getDouble("area"),
                                reader.getDouble("density"),
                                new BoundingBox(xmin,ymin,xmax,ymax));

            longToAdminUnit.put(reader.getLong("id"), tmp);

            units.add(tmp);
            System.out.println(i);
            i++;
        }
        for (AdminUnit a : units) {
            a.setParent(longToAdminUnit.get(a.getParentId()));
        }

        //setting children
        List<AdminUnit> tmpList;
        for (AdminUnit a : units) {
            tmpList = parentid2child.get(a.getParentId());
            if (tmpList == null) {
                tmpList = new ArrayList<>();
            }
            tmpList.add(a);
            parentid2child.put(a.getParentId(), tmpList);
        }
    }

    public void add(AdminUnit unit) {
        units.add(unit);
    }

    /**
     * Function lists all records from 'units' line by line using for loop,
     * parameter given to the output is output of the toString() method.
     * @param out object used to output the result, usually System,out (console)
     */
    public void list(PrintStream out) {
        for (AdminUnit a : units) {
            System.out.println(a.toString());
        }
    }


    /**
     * Function lists records from 'units' line by line with given offset and limit using for loop,
     * parameter given to the output is output of the toString() method.
     * @param out object used to output the result, usually System,out (console)
     * @param offset number of the first records should be omitted
     * @param limit number of records to be listed
     */
    public void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < offset + limit; i++) {
            out.print(units.get(i).toString());
        }
    }

    /**
     * Gets pattern as an argument and creates new AdminUnitList object,
     * Iterates through all units information and compares names of AdminUnits.
     * If the name matches pattern or contains regex (if regex==true),
     * information line is added to the newly created object
    */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit a : units) {
            if (regex && a.name.matches(pattern))
                ret.add(a);
            else if (!regex && a.name.contains(pattern))
                ret.add(a);
        }
        return ret;
    }

    AdminUnitList sortInplaceByName() {
        class AdminUnitComparator implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return t.name.compareTo(t1.name);
            }
        }
        units.sort(new AdminUnitComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea() {
        units.sort(new Comparator<>() {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                if (t.area > t1.area) return -1;
                if (t.area == t1.area) return 0;
                else return 1;
            }
        });
        return this;
    }
    AdminUnitList sortInplacePopulation() {
        units.sort((t, t1) ->  t.population.compareTo(t1.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList copiedUnits = new AdminUnitList();
        copiedUnits.units = new ArrayList<>(this.units);
        copiedUnits.units.sort(cmp);
        return copiedUnits;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList result = new AdminUnitList();
        for (AdminUnit unit : units) {
            if (pred.test(unit)) {
                result.units.add(unit);
            }
        }
        return result;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        AdminUnitList result = new AdminUnitList();
        for (AdminUnit unit : units) {
            if (pred.test(unit)) {
                result.units.add(unit);
                if (result.units.size() == limit) break;
            }
        }
        return result;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList result = new AdminUnitList();
        for (AdminUnit unit : units) {
            if (--offset >= 0) continue;
            if (pred.test(unit)) {
                result.units.add(unit);
                if (result.units.size() == limit) break;
            }
        }
        return result;
    }
}