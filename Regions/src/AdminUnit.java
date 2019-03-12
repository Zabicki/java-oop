import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnit {
    String name;
    int adminLevel;
    Double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox;
    long parentId;
    List<AdminUnit> children;


    public AdminUnit(Long parentId, String name, int adminLevel, double area, double density, BoundingBox bbox) {
        this.parentId = parentId;
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        this.bbox = bbox;
    }

    public String toString() {
        String result = "Name: " + name + "\nAdmin level: " + adminLevel + "\nPopulation: " +
                population + "\nArea: " + area + "\nDensity: " + density +  "xmin: " + bbox.xmin +
                "ymin: " + bbox.ymin +  "xmax: " + bbox.xmax +  "ymax: " + bbox.ymax ;
        return result;
    }

    void setParent(AdminUnit parent) {
        if (adminLevel == 4)
            parent = null;
        else {
            this.parent = parent;
            fixMissingValues();
        }
    }

    long getParentId() {
        return parentId;
    }

    void setChildren() {

    }

    private void fixMissingValues() {
        density = parent.density;
        population = area * density;
    }
}