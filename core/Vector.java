package core;

public interface Vector extends LinearAlgebraObject<Vector> {

    int length();

    double get(int i);
    void set(int i, double value);

    // vector addition
    Vector add(Vector other);
    Vector sub(Vector other);

    // multiplication element by element (Hadamard）
    Vector mulElementWise(Vector other);
    
    // scalar multiplication
    Vector scalarMul(double other);

    // dot product
    double dot(Vector other);

    double norm(); // Euclidean norm
}
