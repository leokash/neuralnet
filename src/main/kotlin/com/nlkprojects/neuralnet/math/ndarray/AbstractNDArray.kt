package com.nlkprojects.neuralnet.math.ndarray

import com.nlkprojects.neuralnet.math.ndarray.utils.NDArrayStringifier

abstract class AbstractNDArray<T: Number>: NDArray<T> {
    override val rows: Int get() = shape.dim1
    override val columns: Int get() = shape.dim2
    override val size: Int get() = rows * columns
    val isScalar: Boolean get() = if (isVector) size == 1 else rows == columns && rows == 1

    override fun toString(): String {
        return NDArrayStringifier().stringify(this)
    }
    override fun errorString(): String {
        return NDArrayStringifier().errorString(this)
    }
    abstract fun validateSize(that: NDArray<*>)
}
