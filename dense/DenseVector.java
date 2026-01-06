package dense;


import core.Vector;
import Exception.DimensionMismatchException;


//Functionality of DenseVector
//1, defined the most faudamental data structure
//2, defined the most basic method, include add, sub, dot, ...
//note: the matrix multiplication in DenseMatrix only consists of two matrix,
//the multiplication between matrix and vector is defined in VectorOps.java
public class DenseVector implements Vector {

    private final int length;
    private final double[] data;

 // basic constructor(using set function to update the data of vector)
    public DenseVector(int length) {
    	this.length = length;
    	this.data = new double[length];
    }

 // a qiucker and more direct constructor
    public DenseVector(double[] data) {
        this.length = data.length;
        this.data = data.clone();
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public double get(int i) {
        return data[i];
    }

    @Override
    public void set(int i, double value) {
        data[i] = value;
    }

    @Override
    public Vector add(Vector other) {
        if (length != other.length())
            throw new DimensionMismatchException();

        DenseVector out = new DenseVector(length);
        for (int i = 0; i < length; i++)
            out.data[i] = data[i] - other.get(i);
        return out;
    }
    
    @Override
    public Vector sub(Vector other) {
    	if (length != other.length())
            throw new DimensionMismatchException();

        DenseVector out = new DenseVector(length);
        for (int i = 0; i < length; i++)
            out.data[i] = data[i] + other.get(i);
        return out;
    }

    @Override
    public Vector mulElementWise(Vector other) {
        if (length != other.length())
            throw new DimensionMismatchException();

        DenseVector out = new DenseVector(length);
        for (int i = 0; i < length; i++)
            out.data[i] = data[i] * other.get(i);
        return out;
    }
    
    @Override
    public DenseVector scalarMul(double other) {
    	DenseVector out = new DenseVector(length);
    	for (int i = 0; i < length; i++)
    		out.data[i] = data[i] * other;
    	return out;
    }

    @Override
    public double dot(Vector other) {
        if (length != other.length())
            throw new DimensionMismatchException();

        double sum = 0.0;
        for (int i = 0; i < length; i++)
            sum += data[i] * other.get(i);
        return sum;
    }

    @Override
    public DenseVector copy() {
        return new DenseVector(data);
    }

    @Override
    public double norm() {
        return Math.sqrt(this.dot(this));
    }

    // scalar add / sub
    @Override
    public Vector add(double scalar) {
        DenseVector out = new DenseVector(length);
        for (int i = 0; i < length; i++)
            out.data[i] = data[i] + scalar;
        return out;
    }

    @Override
    public Vector sub(double scalar) {
        return add(-scalar);
    }
    
    // overriding three most basic method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            sb.append(data[i]);
            if (i < length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DenseVector)) return false;

        DenseVector other = (DenseVector) obj;
        if (this.length != other.length) return false;

        final double eps = 1e-9;
        for (int i = 0; i < length; i++) {
            if (Math.abs(this.data[i] - other.data[i]) > eps)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(length);
        final double eps = 1e-9;
        for (double v : data) {
            long rounded = Math.round(v / eps);
            result = 31 * result + Long.hashCode(rounded);
        }
        return result;
    }

}

