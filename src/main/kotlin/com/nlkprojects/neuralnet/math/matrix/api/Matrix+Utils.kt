package com.nlkprojects.neuralnet.math.matrix.api


import java.lang.RuntimeException
import com.nlkprojects.neuralnet.math.vector.api.Vector

fun Matrix<*>.assertSquare() {
    if(shape.dim1 != shape.dim2)
        throw RuntimeException("Matrix not a square: ${shape.dim1} != ${shape.dim2}")
}
fun Matrix<*>.assertVector(vector: Vector<*>) {
    if (shape.dim2 != vector.size)
        throw RuntimeException("Dimension mismatch: $shape != ${vector.shape}")
}
fun Matrix<*>.assertMultiplication(that: Matrix<*>) {
    if (shape.dim2 != that.shape.dim1)
        throw RuntimeException("Dimension mismatch: matrix multiplication not possible as ${shape.dim2} != ${that.shape.dim1}")
}
