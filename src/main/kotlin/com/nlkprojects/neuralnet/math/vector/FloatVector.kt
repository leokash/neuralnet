package com.nlkprojects.neuralnet.math.vector

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.ndarray.Shape
import com.nlkprojects.neuralnet.math.vector.api.Vector
import com.nlkprojects.neuralnet.math.operator.*
import com.nlkprojects.neuralnet.math.operator.api.Operator

@Serializable
class FloatVector(override val shape: Shape): Vector<Float>(Float::class) {
    constructor(size: Int): this(Shape(size)) { init(0f) }
    constructor(size: Int, value: Float): this(Shape(size)) { init(value) }
    constructor(vararg values: Float): this(Shape(values.size)) { init(values) }
    constructor(size: Int, init: (Int) -> Float): this(Shape(size)) { init(init) }
    constructor(size: Int, axis: Axis = Axis.X, init: (Int) -> Float): this(Shape(size, axis)) { init(init) }

    override lateinit var storage: Array<Float>
    override val operator: Operator<Float> get() = FloatOperator

    init {
        validate()
    }
    private fun init(value: Float) {
        storage = Array(size) { value }
    }
    private fun init(f: (Int) -> Float) {
        storage = Array(size) { f(it) }
    }
    private fun init(values: FloatArray) {
        var idx = 0
        storage = Array(size) { values[idx++] }
    }
}
