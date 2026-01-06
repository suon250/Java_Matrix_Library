# Java Matrix Library

A numerical linear algebra library implemented from scratch in Java, featuring core matrix and vector operations and Gaussian elimination with partial pivoting.

## Features

- DenseMatrix and DenseVector implementations
- Matrix addition, subtraction, scaling, multiplication, and transpose
- Gaussian elimination with partial pivoting for solving linear systems
- Residual computation and singularity checks
- Utility functions for numerical stability (epsilon comparison, etc.)

## Tech Stack

- Java 17+
- No external libraries required

## Status

Core functionality implemented. Further features and optimizations will be added in future updates.

## Example Usage

```java
DenseMatrix A = new DenseMatrix(new double[][]{
    {2, 1},
    {5, 7}
});
DenseVector b = new DenseVector(new double[]{11, 13});

DenseVector x = LinearSolver.solveGaussian(A, b);
System.out.println("Solution: " + x);
