package util;

import core.Matrix;
import core.Vector;
import dense.DenseMatrix;
import dense.DenseVector;

import ops.MatrixOps;
import ops.VectorOps;

public final class MatrixUtils {

    private MatrixUtils() {}

    public static boolean isSquare(Matrix A) {
    	return A.rows() == A.cols();
    }
    
    public static DenseMatrix identity(int n) {
    	double[][] array = new double[n][n];
    	for (int i = 0; i < n; i++) {
    		array[i][i] = 1;
    	}
    	return new DenseMatrix(array);
    }

    public static double residualNorm(Matrix A, Vector x, Vector b) {
        // ||Ax - b||
    	DenseVector Ax = VectorOps.leftMul((DenseVector)x, (DenseMatrix)A);
    	DenseVector AxMinusB = (DenseVector) Ax.sub(b);
    	return VectorOps.norm(AxMinusB);
    }
}

