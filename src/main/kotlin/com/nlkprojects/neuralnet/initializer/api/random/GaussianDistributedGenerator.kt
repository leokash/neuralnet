package com.nlkprojects.neuralnet.initializer.api.random

import com.nlkprojects.neuralnet.initializer.api.RandomGenerator
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.utils.map
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.operator.utils.mulm
import com.nlkprojects.neuralnet.math.operator.api.Operator

@Serializable
data class GaussianDistributedGenerator<T: Number>(
    private val variance: T,
    private val operator: Operator<T>,
    private val usingPseudoRandom: Boolean = true
): RandomGenerator<T> {
    override fun next(): T {
        val rand = Randomizer.random(usingPseudoRandom).nextGaussian()
        return operator.mulm(rand, operator.sqrt(variance))
    }
    override fun init(matrix: Matrix<T>, layer: Int) {
        matrix.map { next() }
    }
}
