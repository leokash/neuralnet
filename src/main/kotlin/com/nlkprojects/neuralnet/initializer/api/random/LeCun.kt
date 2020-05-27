package com.nlkprojects.neuralnet.initializer.api.random

import kotlin.math.sqrt
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.utils.map
import com.nlkprojects.neuralnet.math.operator.utils.mulm
import com.nlkprojects.neuralnet.math.operator.api.Operator
import com.nlkprojects.neuralnet.initializer.api.RandomGenerator

object LeCun {
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
            return operator.mulm(rand, operator.map(1.0 / sqrt(divisor.toDouble())))
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
            return operator.mulm(rand, operator.map(2.0 * sqrt(3.0 / divisor)))
        }
        override fun init(matrix: Matrix<T>, layer: Int) {
            val num = matrix.rows + matrix.columns
            matrix.map { next(num) }
        }
    }
}
