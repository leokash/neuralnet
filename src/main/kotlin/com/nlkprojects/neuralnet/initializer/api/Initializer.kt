package com.nlkprojects.neuralnet.initializer.api

import com.nlkprojects.neuralnet.math.matrix.api.Matrix
import com.nlkprojects.neuralnet.math.ndarray.NDArray
import com.nlkprojects.neuralnet.math.ndarray.utils.map

interface Initializer<T: Number> {
    fun init(): T
    fun init(array: NDArray<T>) {
        array.map { init() }
    }
    fun init(weights: Matrix<T>, layer: Int)
}
