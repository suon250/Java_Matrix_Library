package Test;

import core.Matrix;
import core.Vector;
import dense.DenseVector;
import dense.DenseMatrix;

public class VectorTest {
    public static void main(String[] args) {

        // testing for DenseVector
        Vector v1 = new DenseVector(new double[]{1, 2, 6, 7});
        Vector v2 = new DenseVector(new double[]{3, 4, 5, 6});

        Vector v3 = v1.sub(v2);
        Vector v4 = v1.add(v2);
        Vector v5 = v1.scalarMul(2.0);          // scalar * vector
        Vector v6 = v1.mulElementWise(v2);

        double dot = v1.dot(v2);

        System.out.println("v1       = " + v1);
        System.out.println("v2       = " + v2);
        System.out.println("v1 - v2  = " + v3);
        System.out.println("v1 + v2  = " + v4);
        System.out.println("v1 * 2   = " + v5);
        System.out.println("v1 * v2 (elementwise) = " + v6);
        System.out.println("v1 · v2  = " + dot);
    }
}

