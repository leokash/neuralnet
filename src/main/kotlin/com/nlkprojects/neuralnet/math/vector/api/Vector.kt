package com.nlkprojects.neuralnet.math.vector.api

import kotlin.math.min
import kotlin.math.max
import kotlin.reflect.KClass
import java.lang.RuntimeException
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.utils.noop
import com.nlkprojects.neuralnet.math.ndarray.*
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.utils.matrixOf
import com.nlkprojects.neuralnet.math.ndarray.utils.vectorOf

@Serializable
abstract class Vector<T: Number>(val type: KClass<T>): AbstractNDArray<T>() {

    private val Axis.T: Axis get() = if (this == Axis.X) Axis.Y else Axis.X

    protected abstract val storage: Array<T>
    override val size: Int get() = rows + columns
    override val axis: Axis get() = if (shape.dim1 >= shape.dim2) Axis.X else Axis.Y

    override val isVector: Boolean get() = true
    override val T: Vector<T> get() = vectorOf(type, size, axis.T) { i -> storage[i] }

    protected fun validate() {
        if (min(shape.dim1, shape.dim2) != 0)
            throw IllegalArgumentException("Invalid shape: $shape must a dimension set to 0")
    }

    operator fun get(index: Int): T {
        return storage[index]
    }
    override fun get(x: Int, y: Int): T {
        return storage[x + y]
    }
    operator fun set(index: Int, value: T) {
        storage.set(index, value).noop
    }
    override fun set(x: Int, y: Int, value: T) {
        storage[x + y] = value
    }

    override fun validateSize(that: NDArray<*>) {
        if (!that.isVector || size != that.size)
            throw RuntimeException("Dimension mismatch: $shape != ${that.shape}")
    }

    override fun row(index: Int): List<T> {
        return storage.toList()
    }
    override fun column(index: Int): List<T> {
        return storage.toList()
    }

    override fun forEach(f: (T) -> Unit) {
        storage.forEach(f)
    }
    override fun forEachIndexed(f: (Int, Int, T) -> Unit) {
        for (x in (0 until max(shape.dim1, 1)))
            for (y in (0 until max(shape.dim2, 1)))
                f(x, y, storage[x + y])
    }

    //Math
    operator fun divAssign(that: Vector<T>) {
        validateSize(that)
        mapIndexed { i, num ->
            operator.div(num, that[i])
        }
    }
    operator fun div(that: Vector<T>): Vector<T> {
        validateSize(that)
        return flatMapIndexed { i, num ->
            operator.div(num, that[i])
        }
    }

    operator fun plusAssign(that: Vector<T>) {
        validateSize(that)
        mapIndexed { i, num ->
            operator.add(num, that[i])
        }
    }
    operator fun plus(that: Vector<T>): Vector<T> {
        validateSize(that)
        return flatMapIndexed { i, num ->
            operator.add(num, that[i])
        }
    }

    operator fun minusAssign(that: Vector<T>) {
        validateSize(that)
        mapIndexed { i, num ->
            operator.sub(num, that[i])
        }
    }
    operator fun minus(that: Vector<T>): Vector<T> {
        validateSize(that)
        return flatMapIndexed { i, num ->
            operator.sub(num, that[i])
        }
    }

    operator fun timesAssign(that: Vector<T>) {
        validateSize(that)
        mapIndexed { i, num ->
            operator.mul(num, that[i])
        }
    }
    operator fun times(that: Vector<T>): Vector<T> {
        validateSize(that)
        return flatMapIndexed { i, num ->
            operator.mul(num, that[i])
        }
    }
    operator fun times(matrix: Matrix<T>): Vector<T> {
        return matrix * this
    }

    fun outerProduct(that: Vector<T>): Matrix<T> {
        return matrixOf(type, size, that.size) { x, y ->
            operator.mul(this[x], that[y])
        }
    }

    override fun hashCode(): Int {
        var result = 7
        result = 31 * result + type.hashCode()
        result = 31 * result + axis.hashCode()
        result = 31 * result + shape.hashCode()
        for (i in storage.indices)
            result = 31 * result + storage[i].hashCode()

        return result
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector<*>) return false
        if (type != other.type) return false
        if (shape != other.shape) return false

        for (i in storage.indices)
            if (this[i] != other[i]) return false

        return true
    }
}
