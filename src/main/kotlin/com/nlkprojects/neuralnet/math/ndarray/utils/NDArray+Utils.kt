package com.nlkprojects.neuralnet.math.ndarray.utils

import com.nlkprojects.neuralnet.math.ndarray.NDArray

inline fun <reified T: Number> NDArray<T>.flatMap(noinline f: (T) -> T): NDArray<T> {
    return flatMapIndexed { _, _, num -> f(num)  }
}
inline fun <reified T: Number> NDArray<T>.flatMapIndexed(noinline f: (Int, Int, T) -> T): NDArray<T> {
    return ndarrayFrom(this) { x, y ->
        f(x, y, this[x, y])
    }
}

inline fun <R, T: Number> NDArray<T>.fold(initial: R, crossinline f: (acc: R, T) -> R): R {
    return foldIndexed(initial) { _, _, acc, num -> f(acc, num) }
}
inline fun <R, T: Number> NDArray<T>.foldIndexed(initial: R, crossinline f: (Int, Int, acc: R, T) -> R): R {
    var start = initial
    forEachIndexed { x, y, num ->
        start = f(x, y, start, num)
    }

    return start
}

inline fun <T: Number> NDArray<T>.map(crossinline f: (T) -> T): NDArray<T> {
    return mapIndexed { _, _, num -> f(num) }
}
inline fun <T: Number> NDArray<T>.mapIndexed(crossinline f: (Int, Int, T) -> T): NDArray<T> {
    forEachIndexed { x, y, num ->
        this[x,y] = f(x, y, num)
    }
    return this
}

fun <T: Number> NDArray<T>.toSet(): Set<T> {
    val set = mutableSetOf<T>()
    forEach { set += it }

    return set
}
fun <T: Number> NDArray<T>.toList(): List<T> {
    val set = mutableListOf<T>()
    forEach { set += it }

    return set
}
