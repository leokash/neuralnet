package com.nlkprojects.neuralnet.math.matrix.api


import kotlin.reflect.KClass
import java.lang.RuntimeException
import java.lang.IllegalArgumentException
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.ndarray.*
import com.nlkprojects.neuralnet.math.vector.api.Vector
import com.nlkprojects.neuralnet.math.vector.api.mapIndexed
import com.nlkprojects.neuralnet.math.ndarray.utils.matrixOf
import com.nlkprojects.neuralnet.math.ndarray.utils.vectorOf
import com.nlkprojects.neuralnet.math.ndarray.utils.mapIndexed

@Serializable
abstract class Matrix<T: Number>(val type: KClass<T>): AbstractNDArray<T>() {

    protected abstract val storage: Array<Array<T>>
    override val axis: Axis get() = computeAxis()
    override val isVector: Boolean get() = false
    override val T: Matrix<T> get() = matrixOf(type, shape.dim2, shape.dim1) { x, y -> storage[y][x] }

    val identity: Matrix<T> get() = matrixOf(type, rows, columns) { x, y ->
        if (x == y) operator.one else operator.zero
    }

    private fun computeAxis(): Axis = when {
        shape.dim1 > shape.dim2 -> Axis.X
        shape.dim1 < shape.dim2 -> Axis.Y
        else                    -> Axis.Square
    }

    override fun get(x: Int, y: Int): T {
        return storage[x][y]
    }
    override fun set(x: Int, y: Int, value: T) {
        storage[x][y] = value
    }

    override fun validateSize(that: NDArray<*>) {
        if (shape != that.shape)
            throw RuntimeException("Dimension mismatch: $shape != ${that.shape}")
    }

    override fun row(index: Int): List<T> {
        if (index < 0 || index >= rows)
            throw IllegalAccessException("Out of bounds! $index not in 0..$rows")
        return storage[index].toList()
    }
    override fun column(index: Int): List<T> {
        if (index < 0 || index >= columns)
            throw IllegalAccessException("Out of bounds! $index not in 0..$columns")
        return storage.map { it[index] }.toList()
    }

    override fun forEach(f: (T) -> Unit) {
        forEachIndexed { _, _, num -> f(num) }
    }
    override fun forEachIndexed(f: (Int, Int, T) -> Unit) {
        for (x in (0 until rows))
            for (y in (0 until columns))
                f(x, y, storage[x][y])
    }

    operator fun plusAssign(that: Matrix<T>) {
        validateSize(that)
        mapIndexed { x, y, num ->
            operator.add(num, that.storage[x][y])
        }
    }
    operator fun plusAssign(vector: Vector<T>) {
        val f = vget(vector)
        mapIndexed { x, y, num ->
            operator.add(num, f(x,y))
        }
    }
    operator fun plus(that: Matrix<T>): Matrix<T> {
        validateSize(that)
        return matrixOf(type, rows, columns) { x, y ->
            operator.add(this[x, y], that[x, y])
        }
    }
    operator fun plus(vector: Vector<T>): Matrix<T> {
        val f = vget(vector)
        return matrixOf(type, rows, columns) { x, y ->
            operator.add(this[x, y], f(x, y))
        }
    }

    operator fun minusAssign(that: Matrix<T>) {
        validateSize(that)
        mapIndexed { x, y, num ->
            operator.sub(num, that.storage[x][y])
        }
    }
    operator fun minusAssign(vector: Vector<T>) {
        assertVector(vector)
        mapIndexed { _, y, num ->
            operator.sub(num, vector[y])
        }
    }
    operator fun minus(that: Matrix<T>): Matrix<T> {
        validateSize(that)
        return matrixOf(type, rows, columns) { x, y ->
            operator.sub(this[x, y], that[x, y])
        }
    }
    operator fun minus(vector: Vector<T>): Matrix<T> {
        val f = vget(vector)
        return matrixOf(type, rows, columns) { x, y ->
            operator.sub(this[x, y], f(x, y))
        }
    }

    //Vector element getter function
    private fun vget(vector: Vector<T>): (Int, Int) -> T {
        return when (vector.size) {
            rows    -> { i, _ -> vector[i] }
            columns -> { _, i -> vector[i] }

            else -> throw IllegalArgumentException("Dimension mismatch: $shape != ${vector.shape}")
        }
    }
    //Dot function for matrix and vector operations
    private fun dotf(vector: Vector<T>): Pair<Int, (Int, Matrix<T>, Vector<T>) -> T> {
        return when (vector.size) {
            rows -> columns to { i, m, v -> hdot(i, m, v) }
            columns -> rows to { i, m, v -> vdot(i, m, v) }
            else -> throw IllegalArgumentException("Dimension mismatch: $shape != ${vector.shape}")
        }
    }

    operator fun timesAssign(that: Matrix<T>) {
        assertMultiplication(that)
        mapIndexed { x, y, _ -> dot(x, y, this, that) }
    }
    operator fun timesAssign(vector: Vector<T>) {
        val (_, func) = dotf(vector)
        vector.mapIndexed { i, _ ->
            func(i, this, vector)
        }
    }
    operator fun times(that: Matrix<T>): Matrix<T> {
        assertMultiplication(that)
        return matrixOf(type, shape.dim1, that.shape.dim2) { x, y ->
            dot(x, y, this, that)
        }
    }
    operator fun times(vector: Vector<T>): Vector<T> {
        val (size, func) = dotf(vector)
        return vectorOf(type, size, vector.axis) { i ->
            func(i, this, vector)
        }
    }

    //Element-wise multiplication, Hadamart multiplication

    fun mulAssign(that: Matrix<T>) {
        validateSize(that)
        mapIndexed { x, y, num -> operator.mul(num, that[x, y]) }
    }
    fun mulAssign(vector: Vector<T>) {
        val f = vget(vector)
        mapIndexed { x, y, num ->
            operator.mul(num, f(x,y))
        }
    }
    fun mul(that: Matrix<T>): Matrix<T> {
        validateSize(that)
        return matrixOf(type, rows, columns) { x, y ->
            operator.mul(this[x, y], that[x, y])
        }
    }
    fun mul(vector: Vector<T>): Matrix<T> {
        val f = vget(vector)
        return matrixOf(type, rows, columns) { x, y ->
            operator.mul(this[x, y], f(x, y))
        }
    }

    fun divideAssign(that: Matrix<T>) {
        validateSize(that)
        mapIndexed { x, y, num -> operator.div(num, that[x, y]) }
    }
    fun divideAssign(vector: Vector<T>) {
        val f = vget(vector)
        mapIndexed { x, y, num ->
            operator.div(num, f(x,y))
        }
    }
    fun divide(that: Matrix<T>): Matrix<T> {
        validateSize(that)
        return matrixOf(type, rows, columns) { x, y ->
            operator.div(this[x, y], that[x, y])
        }
    }
    fun divide(vector: Vector<T>): Matrix<T> {
        val f = vget(vector)
        return matrixOf(type, rows, columns) { x, y ->
            operator.div(this[x, y], f(x, y))
        }
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + shape.hashCode()
        for (x in (0 until rows))
            for (y in (0 until columns))
                result = 31 * result + storage[x][y].hashCode()

        return result
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix<*>) return false

        if (type != other.type) return false
        if (shape != other.shape) return false

        for (x in (0 until rows))
            for (y in (0 until columns))
                if(storage[x][y] != other[x,y]) return false

        return true
    }

    companion object {
        //dot product between a matrix and a vector on the y axis
        private fun <T: Number> vdot(x: Int, matrix: Matrix<T>, vector: Vector<T>): T {
            var sum = vector.operator.zero
            for (i in (0 until vector.size))
                sum = vector.operator.add(sum, vector.operator.mul(vector[i], matrix[x, i]))
            return sum
        }
        //dot product between a matrix and a vector on the x axis
        private fun <T: Number> hdot(y: Int, matrix: Matrix<T>, vector: Vector<T>): T {
            var sum = vector.operator.zero
            for (i in (0 until vector.size))
                sum = vector.operator.add(sum, vector.operator.mul(vector[i], matrix[i, y]))
            return sum
        }

        private fun <T: Number> dot(x: Int, y: Int, lhs: Matrix<T>, rhs: Matrix<T>): T {
            var sum = lhs.operator.zero
            for (i in (0 until rhs.shape.dim1))
                sum = lhs.operator.add(sum, lhs.operator.mul(lhs[x,i], rhs[i,y]))
            return sum
        }
    }
}
