package com.nlkprojects.neuralnet.initializer.api.random

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.ndarray.utils.map
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.operator.utils.mulm
import com.nlkprojects.neuralnet.math.operator.api.Operator
import com.nlkprojects.neuralnet.initializer.api.RandomGenerator

@Serializable
data class MinMaxGenerator<T: Number>(
    private val min: T,
    private val max: T,
    private val operator: Operator<T>,
    private val usingPseudoRandom: Boolean = true
): RandomGenerator<T> {
    override fun next(): T {
        val rand = Randomizer.random(usingPseudoRandom).nextDouble()
        val delta = operator.sub(max, min)
        return operator.add(operator.mulm(rand, delta), min)
    }
    override fun init(matrix: Matrix<T>, layer: Int) {
        matrix.map { next() }
    }
}