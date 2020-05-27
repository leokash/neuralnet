package com.nlkprojects.neuralnet.initializer.api

import com.nlkprojects.neuralnet.math.matrix.api.Matrix

interface RandomGenerator<T: Number> {
    fun next(): T
    fun init(matrix: Matrix<T>, layer: Int)
}
