import java.util.Comparator;

import static java.lang.Math.abs;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean isEmpty;

    public BoundingBox() {
        isEmpty = true;
    }

    //as arguments, receive 8 values and determne max and min points
    public BoundingBox(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        isEmpty = false;
    }

    void addPoint(double x, double y) {
        if (x > xmax)
            xmax = x;
        else if (x < xmin)
            xmin = x;
        if (y > ymax)
            ymax = y;
        else if (y < ymin)
            ymin = y;

        isEmpty = false;
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y) {
        if (x <= xmax && x >= xmin && y <= ymax && y >= ymin)
            return true;
        return false;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     *
     * @param bb
     * @return
     */
    //works
    boolean contains(BoundingBox bb) {
        if (bb.xmin >= xmin &&
                bb.ymin >= ymin &&
                bb.xmax <= xmax &&
                bb.ymax <= ymax)
            return true;
        return false;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     *
     * @param bb
     * @return
     */
    //works
    boolean intersects(BoundingBox bb) {
        if (xmin < bb.xmax &&
                xmax > bb.xmin &&
                ymin < bb.ymax &&
                ymax > bb.ymin)
            return true;
        return false;
    }

    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     *
     * @param bb
     * @return
     */
    //works
    BoundingBox add(BoundingBox bb) {
        if (bb.xmax > xmax)
            xmax = bb.xmax;
        if (bb.xmin < xmin)
            xmin = bb.xmin;
        if (bb.ymax > ymax)
            ymax = bb.ymax;
        if (bb.ymin < ymin)
            ymin = bb.ymin;
        return this;
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    //works
    boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    //works
    double getCenterX() {
        if (!isEmpty())
            return xmin + (xmax - xmin) / 2;
        else
            throw new RuntimeException("Bounding box is empty! Cannot calculate CenterX!");
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    //works
    double getCenterY() {
        if (!isEmpty())
            return ymin + (ymax - ymin) / 2;
        else
            throw new RuntimeException("Bounding box is empty! Cannot calculate CenterX!");
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    //works
    double distanceTo(BoundingBox bbx) {
        if (!isEmpty() && !bbx.isEmpty()) {
            final int R = 6371; // Radious of the earth
            Double lat1 = getCenterY();
            Double lon1 = getCenterX();
            Double lat2 = bbx.getCenterY();
            Double lon2 = bbx.getCenterX();
            Double latDistance = toRad(lat2 - lat1);
            Double lonDistance = toRad(lon2 - lon1);
            Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c;
        } else
            throw new RuntimeException("Cannot calculate distance between origins of the bounding boxes!" +
                    "At least one of them is empty!");
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    @Override
    public String toString() {
        return "BoundingBox:" +
                "\nxmin = " + xmin +
                "\nymin = " + ymin +
                "\nxmax = " + xmax +
                "\nymax = " + ymax;
    }
}