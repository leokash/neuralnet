package com.nlkprojects.neuralnet.math.ndarray

interface NDArrayAccess<T> {
    val rows: Int
    val size: Int
    val shape: Shape
    val columns: Int

    operator fun get(x: Int, y: Int): T
    operator fun set(x: Int, y: Int, value: T)
}
