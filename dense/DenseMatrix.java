package dense;

import core.Matrix;
import core.Vector;
import Exception.DimensionMismatchException;

// Functionality of DenseMatrix
// 1, defined the most faudamental data structure
// 2, defined the most basic method, include add, sub, matmul, ...
// note: the matrix multiplication here only consists of two matrix,
// the multiplication between matrix and vector is defined in VectorOps.java
public class DenseMatrix implements Matrix {

    private final int rows, cols;
    private final double[] data; // row-major
    
 // basic constructor(using set function to update the data of matrix)
    public DenseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows * cols];
    }
    
    // a qiucker and more direct constructor
    public DenseMatrix(double[][] array) {
        this.rows = array.length;
        this.cols = (rows > 0) ? array[0].length : 0;
        this.data = new double[rows * cols];
        
        for (int i = 0; i < rows; i++) {
            if (array[i].length != cols) {
                throw new IllegalArgumentException("all the rows must have the same length!");
            }
            System.arraycopy(array[i], 0, data, i * cols, cols);
        }
    }
    

    private int index(int i, int j) {
        return i * cols + j;
    }

    @Override
    public double get(int i, int j) {
        return data[index(i, j)];
    }

    @Override
    public void set(int i, int j, double value) {
        data[index(i, j)] = value;
    }

    @Override
    public Matrix add(Matrix other) {
        if (rows != other.rows() || cols != other.cols())
            throw new DimensionMismatchException();

        DenseMatrix out = new DenseMatrix(rows, cols);
        for (int i = 0; i < data.length; i++)
            out.data[i] = this.data[i] + ((DenseMatrix) other).data[i];
        return out;
    }
    
    @Override
	public Matrix sub(Matrix other) {
		return add(this.add(other.scalarMul(-1)));
	}

    @Override
    public Matrix mulElementWise(Matrix other) {
        if (rows != other.rows() || cols != other.cols())
            throw new DimensionMismatchException();

        DenseMatrix out = new DenseMatrix(rows, cols);
        for (int i = 0; i < data.length; i++)
            out.data[i] = this.data[i] * ((DenseMatrix) other).data[i];
        return out;
    }

    @Override
    public Matrix matmul(Matrix B) {
        if (cols != B.rows())
            throw new DimensionMismatchException();

        DenseMatrix out = new DenseMatrix(rows, B.cols());
        for (int i = 0; i < rows; i++)
            for (int k = 0; k < cols; k++) {
                double aik = get(i, k);
                for (int j = 0; j < B.cols(); j++)
                    out.data[i * out.cols + j] += aik * B.get(k, j);
            }
        return out;
    }

    @Override
    public Vector matmul(Vector v) {
        if (cols != v.length())
            throw new DimensionMismatchException();

        DenseVector out = new DenseVector(rows);
        for (int i = 0; i < rows; i++) {
            double sum = 0;
            for (int j = 0; j < cols; j++)
                sum += get(i, j) * v.get(j);
            out.set(i, sum);
        }
        return out;
    }

    @Override
    public Matrix scalarMul(double scalar) {
        DenseMatrix out = new DenseMatrix(rows, cols);
        for (int i = 0; i < data.length; i++)
            out.data[i] = scalar * data[i];
        return out;
    }

    @Override
    public DenseMatrix copy() {
        DenseMatrix out = new DenseMatrix(rows, cols);
        System.arraycopy(data, 0, out.data, 0, data.length);
        return out;
    }

    @Override
    public int rows() { return rows; }

    @Override
    public int cols() { return cols; }

    @Override
    public int size() { return rows * cols; }

    @Override
    public Matrix add(double scalar) {
        DenseMatrix out = new DenseMatrix(rows, cols);
        for (int i = 0; i < data.length; i++)
            out.data[i] = data[i] + scalar;
        return out;
    }

    @Override
    public Matrix sub(double scalar) {
        return add(-scalar);
    }

    @Override
    public Matrix transpose() {
        DenseMatrix out = new DenseMatrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.set(j, i, get(i, j));
        return out;
    }
    
 // overriding three most basic method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < rows; i++) {
            sb.append("[");
            for (int j = 0; j < cols; j++) {
                sb.append(get(i, j));
                if (j < cols - 1) sb.append(", ");
            }
            sb.append("]");
            if (i < rows - 1) sb.append("\n ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DenseMatrix)) return false;

        DenseMatrix other = (DenseMatrix) obj;
        if (this.rows != other.rows || this.cols != other.cols)
            return false;

        final double eps = 1e-9;
        for (int i = 0; i < data.length; i++) {
            if (Math.abs(this.data[i] - other.data[i]) > eps)
                return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = 31 * Integer.hashCode(rows) + Integer.hashCode(cols);
        final double eps = 1e-9;
        for (double v : data) {
            long rounded = Math.round(v / eps);
            result = 31 * result + Long.hashCode(rounded);
        }
        return result;
    }
}
