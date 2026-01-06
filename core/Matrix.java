package core;

public interface Matrix extends LinearAlgebraObject<Matrix> {

    int rows();
    int cols();

    double get(int i, int j);
    void set(int i, int j, double value);

    // Matrix + Matrix
    Matrix add(Matrix other);
    Matrix sub(Matrix other);

    // multiplication element by element (Hadamard）
    Matrix mulElementWise(Matrix other);

    // stric matrix multiplication
    Matrix matmul(Matrix other);
    
    // scalar multiplication
    Matrix scalarMul(double other);

    // Matrix × Vector
    Vector matmul(Vector v);

    Matrix transpose();
}
