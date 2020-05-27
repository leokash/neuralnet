@file:Suppress("UNCHECKED_CAST")

package com.nlkprojects.neuralnet.math.ndarray.utils

import kotlin.math.max
import kotlin.reflect.KClass
import java.lang.RuntimeException
import com.nlkprojects.neuralnet.math.matrix.*
import com.nlkprojects.neuralnet.math.vector.*
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.ndarray.Shape
import com.nlkprojects.neuralnet.math.ndarray.NDArray
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.vector.api.Vector

inline fun <reified T: Number> vectorOf(vararg values: T): Vector<T> {
    return vectorOf(T::class, values.size) { i -> values[i] }
}
inline fun <reified T: Number> vectorOf(rows: Int, columns: Int): Vector<T> {
    return vectorOf(T::class, rows, columns)
}
inline fun <reified T: Number> vectorOf(value: T, size: Int, axis: Axis = Axis.X): Vector<T> {
    return vectorOf(T::class, size, value, axis)
}
inline fun <reified T: Number> vectorOf(size: Int, axis: Axis = Axis.X, noinline init: (Int) -> T): Vector<T> {
    return vectorOf(T::class, size, axis, init)
}

fun <T: Number> vectorOf(type: KClass<T>, rows: Int, columns: Int): Vector<T> {
    Shape.validateVectorShape(rows, columns)
    return vectorOf(type, max(rows, columns), if (rows >= columns) Axis.X else Axis.Y)
}
fun <T: Number> vectorOf(type: KClass<T>, size: Int, axis: Axis = Axis.X): Vector<T> {
    val value = when (type) {
        Float::class  -> .0f as T
        Double::class -> 0.0 as T
        else      -> throw RuntimeException("Unsupported vector of $type")
    }
    return vectorOf(type, size, value, axis)
}
fun <T: Number> vectorOf(type: KClass<T>, size: Int, value: T, axis: Axis = Axis.X): Vector<T> {
    return vectorOf(type, size, axis) { _ -> value }
}
fun <T: Number> vectorOf(type: KClass<T>, size: Int, axis: Axis = Axis.X, init: (Int) -> T): Vector<T> {
    return when (type) {
        Float::class  -> FloatVector(size, axis, init as (Int) -> Float) as Vector<T>
        Double::class -> DoubleVector(size, axis, init as (Int) -> Double) as Vector<T>
        else          -> throw IllegalArgumentException("Unsupported vector of $type")
    }
}

inline fun <reified T: Number> ndarrayFrom(original: NDArray<T>, noinline init: (Int, Int) -> T = { _,_-> original.operator.zero }): NDArray<T> {
    val size = original.size
    val axis = original.axis
    val (rows, columns) = original.shape
    return when (original) {
        is Matrix -> matrixOf(T::class, rows, columns, init)
        is Vector -> vectorOf(T::class, size, axis) { i ->
            val x = if (axis == Axis.X) i else 0
            val y = if (axis == Axis.Y) i else 0
            init(x, y)
        }

        else -> throw RuntimeException("Unsupported ndarray ${T::class}")
    }
}

inline fun <reified T: Number> matrixOf(rows: Int, columns: Int): Matrix<T> {
    return matrixOf(T::class, rows, columns)
}
inline fun <reified T: Number> matrixOf(value: T, rows: Int, columns: Int): Matrix<T> {
    return matrixOf(value, T::class, rows, columns)
}
inline fun <reified T: Number> matrixOf(rows: Int, columns: Int, vararg values: T): Matrix<T> {
    var i = 0
    return matrixOf(T::class, rows, columns) { _, _ -> values[i++] }
}
inline fun <reified T: Number> matrixOf(rows: Int, columns: Int, noinline init: (Int, Int) -> T): Matrix<T> {
    return matrixOf(T::class, rows, columns, init)
}

fun <T: Number> matrixOf(type: KClass<T>, rows: Int, columns: Int): Matrix<T> {
    return when (type) {
        Float::class  -> FloatMatrix(rows, columns) as Matrix<T>
        Double::class -> DoubleMatrix(rows, columns) as Matrix<T>
        else          -> throw RuntimeException("Unsupported matrix of $type")
    }
}
fun <T: Number> matrixOf(value: T, type: KClass<T>, rows: Int, columns: Int): Matrix<T> {
    return when (type) {
        Float::class  -> FloatMatrix(rows, columns, value as Float) as Matrix<T>
        Double::class -> DoubleMatrix(rows, columns, value as Double) as Matrix<T>
        else          -> throw RuntimeException("Unsupported matrix of $type")
    }
}
fun <T: Number> matrixOf(type: KClass<T>, rows: Int, columns: Int, init: (Int, Int) -> T): Matrix<T> {
    return when (type) {
        Float::class  -> FloatMatrix(rows, columns) { x, y -> init(x,y) as Float } as Matrix<T>
        Double::class -> DoubleMatrix(rows, columns) { x, y -> init(x,y) as Double } as Matrix<T>
        else          -> throw RuntimeException("Unsupported matrix of $type")
    }
}
