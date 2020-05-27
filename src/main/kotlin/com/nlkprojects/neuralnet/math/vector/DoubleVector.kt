package com.nlkprojects.neuralnet.math.vector

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.operator.*
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.ndarray.Shape
import com.nlkprojects.neuralnet.math.vector.api.Vector
import com.nlkprojects.neuralnet.math.operator.api.Operator
import com.nlkprojects.neuralnet.math.vector.api.assertSize

@Serializable
class DoubleVector private constructor(override val shape: Shape): Vector<Double>(Double::class) {
    constructor(size: Int): this(Shape(size)) { init(.0) }
    constructor(size: Int, value: Double): this(Shape(size)) { init(value) }
    constructor(vararg values: Double): this(Shape(values.size)) { init(values) }
    constructor(size: Int, init: (Int) -> Double): this(Shape(size)) { init(init) }
    constructor(size: Int, axis: Axis = Axis.X, init: (Int) -> Double): this(Shape(size, axis)) { init(init) }

    override lateinit var storage: Array<Double>
    override val operator: Operator<Double> get() = DoubleOperator

    init {
        validate()
    }
    private fun init(value: Double) {
        storage = Array(size) { value }
    }
    private fun init(f: (Int) -> Double) {
        storage = Array(size) { f(it) }
    }
    private fun init(values: DoubleArray) {
        var idx = 0
        assertSize(values.size)
        storage = Array(size) { values[idx++] }
    }
}
