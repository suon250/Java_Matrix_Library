package ops;

import dense.DenseMatrix;
import dense.DenseVector;
import Exception.DimensionMismatchException;
import Exception.SingularMatrixException;
import util.MatrixUtils;
import util.NumericalUtils;

public final class LinearSolver {

    private LinearSolver() {}

    /**
     * Solve Ax = b using Gaussian elimination with partial pivoting.
     * A must be square.
     */
    public static DenseVector solveGaussian(DenseMatrix A, DenseVector b) {

        if (!MatrixUtils.isSquare(A)) {
            throw new DimensionMismatchException("Matrix must be square.");
        }
        if (A.rows() != b.size()) {
            throw new DimensionMismatchException("A.rows() != b.size()");
        }

        // copy to avoid modifying inputs
        DenseMatrix U = A.copy();
        DenseVector y = b.copy();

        forwardElimination(U, y);
        return backSubstitution(U, y);
    }

    /**
     * Forward elimination with partial pivoting.
     * Transforms A into an upper triangular matrix U
     * and applies the same row operations to b.
     */
    static void forwardElimination(DenseMatrix A, DenseVector b) {
        int n = A.rows();

        for (int k = 0; k < n - 1; k++) {

            // 1. pivot selection
            int pivotRow = k;
            double max = Math.abs(A.get(k, k));

            for (int i = k + 1; i < n; i++) {
                double val = Math.abs(A.get(i, k));
                if (val > max) {
                    max = val;
                    pivotRow = i;
                }
            }

            if (NumericalUtils.isZero(max)) {
                throw new SingularMatrixException("Matrix is singular.");
            }

            // 2. row swap
            if (pivotRow != k) {
                MatrixOps.swapRows(A, k, pivotRow);
                VectorOps.swap(b, k, pivotRow);
            }

            // 3. elimination
            for (int i = k + 1; i < n; i++) {
                double factor = A.get(i, k) / A.get(k, k);

                // eliminate A[i][k]
                for (int j = k; j < n; j++) {
                    A.set(i, j, A.get(i, j) - factor * A.get(k, j));
                }

                // apply to RHS
                b.set(i, b.get(i) - factor * b.get(k));
            }
        }
    }

    // Solve Ux = y where U is upper triangular.
    static DenseVector backSubstitution(DenseMatrix U, DenseVector y) {
        int n = U.rows();
        DenseVector x = new DenseVector(n);

        for (int i = n - 1; i >= 0; i--) {
            double sum = y.get(i);

            for (int j = i + 1; j < n; j++) {
                sum -= U.get(i, j) * x.get(j);
            }

            double pivot = U.get(i, i);
            if (NumericalUtils.isZero(pivot)) {
                throw new SingularMatrixException("Zero pivot in back substitution.");
            }

            x.set(i, sum / pivot);
        }

        return x;
    }
}
