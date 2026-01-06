package core;

// an universal interface for both DenseMAtrix and DenseVector
// use genrics since the object can be both matrix and vector
public interface LinearAlgebraObject<T> {

    int size(); // return the size of the object
    T copy();

    // scalar operation must be valid
    // when considering operation between matrix and vector, we use overloading method
    T add(double scalar);
    T sub(double scalar);
    T scalarMul(double scalar);     // scalar multiplication
}
