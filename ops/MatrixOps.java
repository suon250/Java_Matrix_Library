package ops;

import core.Matrix;
import core.Vector;
import dense.DenseMatrix;
import dense.DenseVector;


public final class MatrixOps {

    private MatrixOps() {} // 禁止实例化--private constructor

    public static void swapRows(DenseMatrix A, int i, int j) {
        // row i ↔ row j
    	for (int x = 0; x < A.cols(); x++) {
    		double tmp = A.get(i, x);
    		A.set(i,  x,  A.get(j, x));
    		A.set(j,  x,  tmp);
    	}
    }
    
    // matrix transpose is defined inside DesnseMatrix
    // matrix matmul is defined inside DenseMatrix
    
    public static double norm(DenseMatrix A) {
        double sum = 0.0;
        int row = A.rows();
        int col = A.cols();
        
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		double value = A.get(i, j);
        		sum += value * value;
        	}
        }
        
        return Math.sqrt(sum);
    }
}
