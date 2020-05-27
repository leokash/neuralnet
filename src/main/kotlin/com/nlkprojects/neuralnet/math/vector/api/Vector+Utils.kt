package com.nlkprojects.neuralnet.math.vector.api

import com.nlkprojects.neuralnet.math.ndarray.utils.vectorOf

fun Vector<*>.assertSize(size: Int) {
    if (this.size != size)
        throw IllegalArgumentException("Size mismatch: ${this.size} != $size")
}
inline fun <T: Number> Vector<T>.forEachIndexed(f: (Int, T) -> Unit) {
    for (i in (0 until size))
        f(i, this[i])
}
inline fun <T: Number> Vector<T>.mapIndexed(f: (Int, T) -> T): Vector<T> {
    for (i in (0 until size))
        this[i] = f(i, this[i])
    return this
}
inline fun <T: Number> Vector<T>.flatMapIndexed(crossinline f: (Int, T) -> T): Vector<T> {
    return vectorOf(type, size, axis) { i -> f(i, this[i]) }
}
inline fun <T: Number, R> Vector<T>.foldIndexed(initial: R, f: (Int, acc: R, T) -> R): R {
    var start = initial
    for (i in (0 until size))
        start = f(i, start, this[i])
    return start
}
