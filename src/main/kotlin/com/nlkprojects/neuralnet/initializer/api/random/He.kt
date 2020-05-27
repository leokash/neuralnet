package com.nlkprojects.neuralnet.initializer.api.random

import kotlin.math.sqrt
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.utils.map
import com.nlkprojects.neuralnet.math.operator.utils.mulm
import com.nlkprojects.neuralnet.math.operator.api.Operator
import com.nlkprojects.neuralnet.initializer.api.RandomGenerator

/**
 * Best suited for use with ReLU, Leaky ReLU activation functions
 * https://towardsdatascience.com/weight-initialization-techniques-in-neural-networks-26c649eb3b78
 */
object He {
    @Serializable
    data class Normal<T : Number>(
        private val operator: Operator<T>,
        private val usingPseudoRandom: Boolean = true
    ): RandomGenerator<T> {
        override fun next(): T {
            return next(1)
        }
        private fun next(divisor: Int): T {
            val rand = Randomizer.random(usingPseudoRandom).nextGaussian()
            return operator.mulm(rand, operator.map(sqrt(2.0 / divisor)))
        }
        override fun init(matrix: Matrix<T>, layer: Int) {
            val num = matrix.columns
            matrix.map { next(num) }
        }
    }

    @Serializable
    data class Uniform<T : Number>(
        private val operator: Operator<T>,
        private val usingPseudoRandom: Boolean = true
    ): RandomGenerator<T> {
        override fun next(): T {
            return next(1)
        }
        private fun next(divisor: Int): T {
            val rand = Randomizer.random(usingPseudoRandom).nextDouble() - .5
            return operator.mulm(rand, operator.map(2.0 * sqrt(6.0 / divisor)))
        }
        override fun init(matrix: Matrix<T>, layer: Int) {
            val num = matrix.rows + matrix.columns
            matrix.map { next(num) }
        }
    }
}
