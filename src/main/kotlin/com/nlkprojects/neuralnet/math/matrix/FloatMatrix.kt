package com.nlkprojects.neuralnet.math.matrix

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.ndarray.Shape
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.operator.*
import com.nlkprojects.neuralnet.math.operator.api.Operator

@Serializable
class FloatMatrix private constructor(override val shape: Shape): Matrix<Float>(Float::class) {
    constructor(rows: Int, columns: Int): this(Shape(rows, columns)) { init(.0f) }
    constructor(rows: Int, columns: Int, value: Float): this(Shape(rows, columns))  { init(value) }
    constructor(rows: Int, columns: Int, vararg values: Float): this(Shape(rows, columns)) { init(values) }
    constructor(rows: Int, columns: Int, init: (Int, Int) -> Float): this(Shape(rows, columns))  { init(init) }

    override lateinit var storage: Array<Array<Float>>
    override val operator: Operator<Float> get() = FloatOperator

    private fun init(value: Float) {
        storage = Array(shape.dim1) { Array(shape.dim2) { value } }
    }
    private fun init(values: FloatArray) {
        var idx = 0
        storage = Array(shape.dim1) { Array(shape.dim2) { values[idx++] } }
    }
    private fun init(f: (Int, Int) -> Float) {
        storage = Array(shape.dim1) { x -> Array(shape.dim2) { y -> f(x,y) } }
    }
}
