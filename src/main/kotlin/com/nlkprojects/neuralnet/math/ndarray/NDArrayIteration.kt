package com.nlkprojects.neuralnet.math.ndarray

interface NDArrayIteration<T> {
    fun forEach(f: (T) -> Unit)
    fun forEachIndexed(f: (Int, Int, T) -> Unit)
}
