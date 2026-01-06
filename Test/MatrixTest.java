package Test;

import core.Matrix;
import core.Vector;
import dense.DenseVector;
import dense.DenseMatrix;

public class MatrixTest {
    public static void main(String[] args) {

        Matrix A = new DenseMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });

        Matrix B = new DenseMatrix(new double[][]{
            {5, 6},
            {7, 8}
        });

        Vector x = new DenseVector(new double[]{1, 1});
        Vector y = new DenseVector(new double[]{2, 3});

        Matrix C1 = A.add(B);
        Matrix C2 = A.sub(B);
        Matrix C3 = A.mulElementWise(B);        // element-wise
        Matrix C4 = A.scalarMul(2.0);      // scalar
        Matrix AT = A.transpose();

        Vector Ax = A.matmul(x);
        Matrix AB = A.matmul(B); // strict matrix multiplication

        System.out.println("A =\n" + A);
        System.out.println("B =\n" + B);

        System.out.println("A + B =\n" + C1);
        System.out.println("A - B =\n" + C2);
        System.out.println("A ⊙ B =\n" + C3);
        System.out.println("A * 2 =\n" + C4);
        System.out.println("A^T =\n" + AT);

        System.out.println("x = " + x);
        System.out.println("A x = " + Ax);

        System.out.println("A B =\n" + AB);
    }
}

