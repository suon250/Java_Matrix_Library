package ops;

import core.Matrix;
import core.Vector;
import dense.DenseMatrix;
import dense.DenseVector;
import Exception.DimensionMismatchException;

public final class VectorOps {

    private VectorOps() {}

    // vector dot is defined inside DenseMatrix
    // use 2-norm
    public static double norm(DenseVector v) {
        double sum = 0.0;
        int n = v.length();
        
        for (int i = 0; i < n; i++) {
            double val = v.get(i);
            sum += val * val;
        }
        
        return Math.sqrt(sum);
    }
    
    // rightMul means that we are multiply A on the right of x,
    // and x will be considered a row vector instead of column vector
    public static DenseVector rightMul(DenseVector x, DenseMatrix A) {
        // 检查维度：行向量的长度必须等于矩阵的行数
        if (x.length() != A.rows()) {
            throw new DimensionMismatchException(
                String.format("Dimension dismatch：row(%d) ≠ column(%d)", 
                x.length(), A.rows()));
        }
        
        int resultSize = A.cols();
        DenseVector result = new DenseVector(resultSize);
        
        // 计算：y[j] = Σ_i x[i] * A[i][j]
        for (int j = 0; j < resultSize; j++) {
            double sum = 0.0;
            for (int i = 0; i < x.length(); i++) {
                sum += x.get(i) * A.get(i, j);
            }
            result.set(j, sum);
        }
        
        return result;
    }
    
    // leftMul means that we are multiply A on the left of x,
    // and x will be considered a column vector instead of row vector
    public static DenseVector leftMul(DenseVector x, DenseMatrix A) {
        // 检查维度：矩阵的列数必须等于列向量的长度
        if (A.cols() != x.length()) {
            throw new DimensionMismatchException(
            	String.format("Dimension dismatch：row(%d) ≠ column(%d)", 
                A.cols(), x.length()));
        }
        
        int resultSize = A.rows();
        DenseVector result = new DenseVector(resultSize);
        
        // 计算：y[i] = Σ_j A[i][j] * x[j]
        for (int i = 0; i < resultSize; i++) {
            double sum = 0.0;
            for (int j = 0; j < x.length(); j++) {
                sum += A.get(i, j) * x.get(j);
            }
            result.set(i, sum);
        }
        
        return result;
    }
    
    // a more convenient and direct method
    public static DenseVector matrixTimesVector(DenseMatrix A, DenseVector x) {
        return leftMul(x, A);  // A × x
    }
    
    // a more convenient and direct method
    public static DenseVector vectorTimesMatrix(DenseVector x, DenseMatrix A) {
        return rightMul(x, A);  // x × A
    }
    
    /**
     * 测试方法
     */
    public static void test() {
        System.out.println("=== 测试右乘 (行向量 × 矩阵) ===");
        
        // 创建测试行向量 (1×3)
        DenseVector rowVector = new DenseVector(new double[]{1, 2, 3});
        System.out.println("行向量 x = " + rowVector);
        
        // 创建测试矩阵 (3×2)
        DenseMatrix matrix = new DenseMatrix(new double[][]{
            {4, 5},
            {6, 7},
            {8, 9}
        });
        System.out.println("矩阵 A = \n" + matrix);
        
        // 右乘：x × A = y (1×2)
        DenseVector resultRight = VectorOps.rightMul(rowVector, matrix);
        System.out.println("右乘结果 y = x × A = " + resultRight);
        System.out.println("期望值: [1*4+2*6+3*8, 1*5+2*7+3*9] = [40.0, 46.0]");
        
        System.out.println("\n=== 测试左乘 (矩阵 × 列向量) ===");
        
        // 创建测试列向量 (2×1)
        DenseVector colVector = new DenseVector(new double[]{2, 3});
        System.out.println("列向量 x = " + colVector);
        
        // 左乘：A × x = y (3×1)
        DenseVector resultLeft = VectorOps.leftMul(colVector, matrix);
        System.out.println("左乘结果 y = A × x = " + resultLeft);
        System.out.println("期望值: [4*2+5*3, 6*2+7*3, 8*2+9*3] = [23.0, 33.0, 43.0]");
        
        System.out.println("\n=== 测试维度不匹配异常 ===");
        try {
            // 这个应该抛出异常
            VectorOps.rightMul(new DenseVector(2), matrix);
            System.out.println("错误：应该抛出异常但没有抛出");
        } catch (DimensionMismatchException e) {
            System.out.println("正确捕获异常: " + e.getMessage());
        }
    }
    
    public static void swap(DenseVector v, int i, int j) {
        if (i == j) return;

        double temp = v.get(i);
        v.set(i, v.get(j));
        v.set(j, temp);
    }

    
    public static void main(String[] args) {
        test();
    }
}