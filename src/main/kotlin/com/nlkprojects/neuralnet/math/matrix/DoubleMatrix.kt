package com.nlkprojects.neuralnet.math.matrix

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.ndarray.Shape
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.operator.*
import com.nlkprojects.neuralnet.math.operator.api.Operator

@Serializable
class DoubleMatrix private constructor(override val shape: Shape): Matrix<Double>(Double::class) {
    constructor(rows: Int, columns: Int): this(Shape(rows, columns)) { init(.0) }
    constructor(rows: Int, columns: Int, value: Double): this(Shape(rows, columns))  { init(value) }
    constructor(rows: Int, columns: Int, vararg values: Double): this(Shape(rows, columns)) { init(values) }
    constructor(rows: Int, columns: Int, init: (Int, Int) -> Double): this(Shape(rows, columns))  { init(init) }

    override lateinit var storage: Array<Array<Double>>
    override val operator: Operator<Double> get() = DoubleOperator

    private fun init(value: Double) {
        storage = Array(shape.dim1) { Array(shape.dim2) { value } }
    }
    private fun init(values: DoubleArray) {
        var idx = 0
        storage = Array(shape.dim1) { Array(shape.dim2) { values[idx++] } }
    }
    private fun init(f: (Int, Int) -> Double) {
        storage = Array(shape.dim1) { x -> Array(shape.dim2) { y -> f(x,y) } }
    }
}
