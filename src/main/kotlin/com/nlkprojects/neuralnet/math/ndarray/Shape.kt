package com.nlkprojects.neuralnet.math.ndarray

import java.lang.RuntimeException
import kotlinx.serialization.Serializable
import java.lang.IllegalArgumentException

@Serializable
data class Shape(val dim1: Int, val dim2: Int = 0) {
    val T: Shape get() = Shape(dim2, dim1)

    init {
        if (dim1 < 0 || dim2 < 0)
            throw IllegalArgumentException("Invalid shape: $this cannot have negative dimensions")
    }

    override fun toString(): String {
        return "(${dim1.string()},${dim2.string()})"
    }
    private fun Int.string(): String {
        return if (this == 0) "" else this.toString()
    }

    companion object {
        fun validateVectorShape(rows: Int, columns: Int) {
            if (rows > 0 && columns > 0)
                throw IllegalArgumentException("vector must have one of its dimensions set to zero. dim1: $rows, dim2: $columns")
        }
        operator fun invoke(size: Int, axis: Axis): Shape {
            return when (axis) {
                Axis.X -> Shape(size, 0)
                Axis.Y -> Shape(0, size)

                else -> throw RuntimeException("Invalid shape from axis: $axis")
            }
        }
    }
}
