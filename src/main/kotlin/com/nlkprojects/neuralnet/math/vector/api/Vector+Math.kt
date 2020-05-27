package com.nlkprojects.neuralnet.math.vector.api

import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.utils.vectorOf

fun <T: Number> min(lhs: Vector<T>, rhs: Vector<T>): Vector<T> {
    lhs.validateSize(rhs)
    val op = lhs.operator
    return vectorOf(lhs.type, lhs.size, lhs.axis) { i -> op.min(lhs[i], rhs[i]) }
}
fun <T: Number> max(lhs: Vector<T>, rhs: Vector<T>): Vector<T> {
    lhs.validateSize(rhs)
    val op = lhs.operator
    return vectorOf(lhs.type, lhs.size, lhs.axis) { i -> op.max(lhs[i], rhs[i]) }
}

fun <T: Number> dot(lhs: Vector<T>, rhs: List<T>): T {
    val op = lhs.operator
    lhs.assertSize(rhs.size)
    return lhs.foldIndexed(op.zero) { i, acc, num -> op.add(acc, op.mul(num, rhs[i])) }
}
fun <T: Number> dot(vector: Vector<T>, matrix: Matrix<T>): Vector<T> {
    return matrix * vector
}
