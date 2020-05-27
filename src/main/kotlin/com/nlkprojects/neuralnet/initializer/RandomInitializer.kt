package com.nlkprojects.neuralnet.initializer

import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.initializer.api.Initializer
import com.nlkprojects.neuralnet.initializer.api.RandomGenerator

@Serializable
data class RandomInitializer<T: Number>(private val rand: RandomGenerator<T>): Initializer<T> {
    override fun init(): T = rand.next()
    override fun init(weights: Matrix<T>, layer: Int) {
        rand.init(weights, layer)
    }
}
