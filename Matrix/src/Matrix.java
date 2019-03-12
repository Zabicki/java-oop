/*
Matrix stores data in one-dimensional array, not two-dimensional one
- Change constructors and printMatrix method to operate on one-dimensional array
- Consider how to clone objects state from the super object

 */

public class Matrix {
    double []data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }
    Matrix(double[][] d) {
        int longestRow = d[0].length;
        int[] rowsLength = new int[d.length]; //stores lengths of every row of matrix
        int tmp;

        //determine longest row
        for (int i = 0; i < d.length; i++) {
            tmp = 0;
            for (int j = 0; j < d[i].length; j++) {
                tmp++;
            }
            if (tmp > longestRow)
                longestRow = tmp;
            rowsLength[i] = tmp;
        }
        this.rows = d.length;
        this.cols = longestRow;
        data = new double[rows * cols];
        double[][] dataArray = new double[rows][cols];
        int j;
        for (int i = 0; i < rows; i++) {
            for (j = 0; j < rowsLength[i]; j++) {
                dataArray[i][j] = d[i][j];
            }
            while (j < longestRow) {
                dataArray[i][j] = 0.0;
                j++;
            }
        }

        int iterator = 0;
        int iterator_col = 0;
        for (int i = 0; i < rows; i++) {
            iterator_col = 0;
            for (j = 0; j < cols; j++) {
                data[iterator] = dataArray[i][iterator_col];
                iterator_col++;
                iterator++;
            }
        }
    }

    double[][] asArray() {
        double[][] tab = new double[rows][cols];
        int it = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tab[i][j] = data[it];
                it++;
            }
        }
        return tab;
    }
    double get(int r, int c) {
        double[][] tab = asArray();
        return tab[r-1][c-1];
    }

    void set(double r, double c, double value) {
        double position = (r-1) * cols + c - 1;
        data[(int)position] = value;
    }
    int[] shape() {
        int[] shape = new int[]{rows, cols};
        return shape;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        for(int i=0;i<rows*cols;i++){
            if (i % rows == 0)
                buf.append("\n");
            buf.append("[" + data[i] + "] ");
        }
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        cols = newCols;
        rows = newRows;
    }

    Matrix add(Matrix m) {
        if (m.cols == this.cols && m.rows == this.rows) {
            Matrix result = new Matrix(rows,cols);
            for (int i = 0; i < rows * cols; i++) {
                result.data[i] = this.data[i] + m.data[i];
            }
            return result;
        }
        return null;
    }

    Matrix sub(Matrix m) {
        if (m.cols == this.cols && m.rows == this.rows) {
            Matrix result = new Matrix(rows,cols);
            for (int i = 0; i < rows * cols; i++) {
                result.data[i] = this.data[i] - m.data[i];
            }
            return result;
        }
        return null;
    }

    Matrix mul(Matrix m) {
        if (m.cols == this.cols && m.rows == this.rows) {
            Matrix result = new Matrix(rows,cols);
            for (int i = 0; i < rows * cols; i++) {
                result.data[i] = this.data[i] * m.data[i];
            }
            return result;
        }
        return null;
    }

    Matrix div(Matrix m) {
        if (m.cols == this.cols && m.rows == this.rows) {
            Matrix result = new Matrix(rows, cols);
            for (int i = 0; i < rows * cols; i++) {
                result.data[i] = this.data[i] / m.data[i];
            }
            return result;
        }
        return null;
    }

    Matrix add(double v) {
        Matrix result = new Matrix(rows,cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = this.data[i] + v;
        }
        return result;
    }

    Matrix sub(double v) {
        Matrix result = new Matrix(rows,cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = this.data[i] - v;
        }
        return result;
    }

    Matrix mul(double v) {
        Matrix result = new Matrix(rows,cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = this.data[i] * v;
        }
        return result;
    }

    Matrix div(double v) {
        Matrix result = new Matrix(rows,cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = this.data[i] / v;
        }
        return result;
    }

    Matrix dot(Matrix m) {
        if (this.cols == m.rows) {
            double[][] m1 = this.asArray();
            double[][] m2 = m.asArray();
            Matrix result = new Matrix(this.rows, m.cols);
            double[][] m3 = result.asArray();

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < m.cols; j++) {
                    for (int k = 0; k < this.cols; k++) {
                        m3[i][j] += m1[i][k] * m2[k][j];
                    }
                }
            }

            int iterator = 0;
            int iterator_cols;
            for (int i = 0; i < rows; i++) {
                iterator_cols = 0;
                for (int j = 0; j < cols; j++) {
                    result.data[iterator] = m3[i][iterator_cols];
                    iterator++;
                    iterator_cols++;
                }
            }
            return result;
        }
        return null;
    }

    double frobenius() {
        double result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += data[i] * data[i];
            }
        }
        result = Math.sqrt(result);
        return result;
    }

    //Kartkówka 1 - Grupa A

    //funkcja zwraca i-ta kolumne macierzy
    Matrix getColumn(int i) {
        if (i < 0 || i > cols) {
            throw new RuntimeException();
        }
        Matrix result = new Matrix(rows,1);
        int iterator = i;
        for (int j = 0; j < rows; j++) {
            result.set(j+1,1, data[iterator]);
            iterator += rows;
        }

        return result;
    }

    public static void main(String[] args) {
        /*Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix m2 = new Matrix(new double[][]{{4,3,2,1},{6,5},{8,7},{9}});
        Matrix m3 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m4 = new Matrix(new double[][]{{1,2,3},{4,5,6},{6,7,8}});
        Matrix m5 = m3.dot(m4);
        System.out.println(m5.toString());*/

        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.getColumn(0);
        System.out.println(m.toString());
        System.out.println(col.toString());
    }
}