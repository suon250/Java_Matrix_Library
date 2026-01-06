package Test;

import ops.LinearSolver;
import util.MatrixUtils;
import dense.DenseMatrix;
import dense.DenseVector;

public class LinearSolverTest {
	public static void main(String[] args) {
		DenseMatrix A = new DenseMatrix(new double[][]{
		    {2, 1},
		    {5, 7}
		});
		DenseVector b = new DenseVector(new double[]{11, 13});

		DenseVector x = LinearSolver.solveGaussian(A, b);

		System.out.println(x); // 应该接近 [7.111..., -3.222...]
		System.out.println(MatrixUtils.residualNorm(A, x, b));

	}
}
