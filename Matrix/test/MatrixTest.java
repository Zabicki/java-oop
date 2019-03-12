import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Random;

public class MatrixTest {
    private Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(2,2);
    }

    @Test
    public void MatrixRowsCols() {
        matrix = new Matrix(3,7);
        assertEquals("Wiersze nie maja identycznych wartosci", matrix.rows, 3);
        assertEquals("Kolumny nie maja identycznych wartosci", matrix.cols, 7);
    }

    @Test
    public void Matrix2DArray() {
        matrix = new Matrix(new double[][]{{1, 2, 3}, {1, 2}, {1}});
        double[][] array = matrix.asArray();
        double tab[] = {3, 2, 1};

        assertEquals("Dlugosc wierszy jest nieprawidlowa", array[0].length, 3);
        assertEquals("Dlugosc wierszy jest nieprawidlowa", array[1].length, 3);
        assertEquals("Dlugosc wierszy jest nieprawidlowa", array[2].length, 3);

        assertEquals("Podana wartosc powinna miec wartosc 0", (int)array[1][2], 0);
        assertEquals("Podana wartosc powinna miec wartosc 0", (int)array[2][1], 0);
        assertEquals("Podana wartosc powinna miec wartosc 0", (int)array[2][2], 0);
    }

    @Test
    public void asArray() {
        double[][] tab = new double[][]{{5,3,1}, {1}, {2,1}};
        matrix = new Matrix(tab);
        double[][] array = matrix.asArray();

        int iteratorCol;
        for (int i = 0; i < matrix.rows; i++) {
            iteratorCol = 0;
            for (int j = 0; j < tab[i].length; j++) {
                assertEquals("Wartosci w wierszach roznia sie", (int)tab[i][iteratorCol], (int)array[i][iteratorCol]);
                iteratorCol++;
            }
        }
    }

    @Test
    public void get() {
        matrix = new Matrix(new double[][]{{1,2},{3,4}});
        double[][] array = matrix.asArray();
        assertEquals("Values do not match each other", (int)matrix.get(1,1), 1);
        assertEquals("Values do not match each other", (int)matrix.get(1,2), 2);
        assertEquals("Values do not match each other", (int)matrix.get(2,1), 3);
        assertEquals("Values do not match each other", (int)matrix.get(2,2), 4);
    }

    @Test
    public void set() {
        matrix.set(1,1, 4);
        matrix.set(1,2, 3);
        matrix.set(2,1, 2);
        matrix.set(2,2, 1);

        assertEquals("Values do not match each other", (int)matrix.get(1,1), 4);
        assertEquals("Values do not match each other", (int)matrix.get(1,2), 3);
        assertEquals("Values do not match each other", (int)matrix.get(2,1), 2);
        assertEquals("Values do not match each other", (int)matrix.get(2,2), 1);
    }

    @Test
    public void shape() {
        int[] tab = matrix.shape();
        assertEquals(tab[0], matrix.rows);
        assertEquals(tab[1], matrix.cols);
    }

    @Test
    public void toStringTest() {

    }

    @Test
    public void reshape() {
    }

    @Test
    public void add() {
    }

    @Test
    public void sub() {
    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void add1() {
    }

    @Test
    public void sub1() {
    }

    @Test
    public void mul1() {
    }

    @Test
    public void div1() {
    }

    @Test
    public void dot() {
    }

    @Test
    public void frobenius() {
    }

    @Test
    public void main() {
    }
}