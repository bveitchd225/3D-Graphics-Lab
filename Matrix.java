public class Matrix {

    private double[][] values;

    public Matrix(double[][] v) {
        values = new double[v.length][v[0].length];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                this.values[i][j] = v[i][j];
            }
        }
    }

    public static Matrix getIdentityMatrix(int size) {
        double[][] vals = new double[size][size];
        for (int n = 0; n < size; n++) {
            vals[n][n] = 1.0;
        }
        return new Matrix(vals);
    }

    public Matrix mult(Matrix other) {

        double[][] nv = new double[other.values.length][this.values.length];
        for (int i = 0; i < nv.length; i++) {
            for (int j = 0; j < nv[i].length; j++) {
                for (int n = 0; n < nv.length; n++) {
                    nv[i][j] += this.values[i][n]*other.values[n][j];
                } 
            }
        }
        return new Matrix(nv);
    }

    
    public static Matrix getZRotationMatrix(double degrees) {
        double r = Math.toRadians(degrees);
        double[][] v = new double[][]{
            {  Math.cos(r), -Math.sin(r),            0, 0},
            {  Math.sin(r),  Math.cos(r),            0, 0},
            {            0,            0,            1, 0},
            {            0,            0,            0, 1}
        };
        return new Matrix(v);
    }

    public static Matrix getYRotationMatrix(double degrees) {
        double r = Math.toRadians(degrees);
        double[][] v = new double[][]{
            {  Math.cos(r),            0,  Math.sin(r), 0},
            {            0,            1,            0, 0},
            { -Math.sin(r),            0,  Math.cos(r), 0},
            {            0,            0,            0, 1}
        };
        return new Matrix(v);
    }

    public static Matrix getXRotationMatrix(double degrees) {
        double r = Math.toRadians(degrees);
        double[][] v = new double[][]{
            {           1,            0,            0, 0},
            {           0,  Math.cos(r), -Math.sin(r), 0},
            {           0,  Math.sin(r),  Math.cos(r), 0},
            {           0,            0,            0, 1}
        };
        return new Matrix(v);
    }

    public double[][] getValues() {
        return values;
    }
}
