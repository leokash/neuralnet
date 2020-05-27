package com.nlkprojects.neuralnet.math.ndarray

import com.nlkprojects.neuralnet.math.operator.api.Operator

interface NDArray<T: Number>: NDArrayAccess<T>, NDArrayIteration<T> {
    val T: NDArray<T>
    val axis: Axis
    val isVector: Boolean
    val operator: Operator<T>

    fun errorString(): String

    fun row(index: Int): List<T>
    fun column(index: Int): List<T>
}
