package com.nlkprojects.neuralnet.math.ndarray.utils

import java.lang.RuntimeException
import com.nlkprojects.neuralnet.math.ndarray.NDArray

fun <T: Number> dot(arr: NDArray<T>, scalar: T): T {
    val op = arr.operator
    return arr.fold(op.zero) { acc, t -> op.add(acc, op.mul(scalar, t)) }
}
fun <T: Number> dot(lhs: NDArray<T>, rhs: NDArray<T>): T {
    if (lhs.shape != rhs.shape)
        throw RuntimeException("Dimension mismatch: ${lhs.shape} != ${rhs.shape}")
    val op = lhs.operator
    return lhs.foldIndexed(op.zero) { x, y, acc, num -> op.add(acc, op.mul(num, rhs[x, y]))}
}
fun <T: Number> equals(lhs: NDArray<T>, rhs: NDArray<T>, eps: T): Boolean {
    var b = true
    val op = lhs.operator
    lhs.forEachIndexed { x, y, num ->
        if (!op.equals(num, rhs[x,y], eps)) {
            b = false
            return@forEachIndexed
        }
    }

    return b
}

inline operator fun <reified T: Number> NDArray<T>.unaryPlus(): NDArray<T> {
    return flatMap { operator.add(it, operator.one) }
}
inline operator fun <reified T: Number> NDArray<T>.unaryMinus(): NDArray<T> {
    return flatMap { operator.sub(it, operator.one) }
}

operator fun <T: Number> T.divAssign(arr: NDArray<T>) {
    val op = arr.operator
    arr.map { op.div(this, it) }
}
operator fun <T: Number> T.plusAssign(arr: NDArray<T>) {
    val op = arr.operator
    arr.map { op.add(this, it) }
}
operator fun <T: Number> T.minusAssign(arr: NDArray<T>) {
    val op = arr.operator
    arr.map { op.sub(this, it) }
}
operator fun <T: Number> T.timesAssign(arr: NDArray<T>) {
    val op = arr.operator
    arr.map { op.mul(this, it) }
}

operator fun <T: Number> NDArray<T>.divAssign(scalar: T) {
    map { operator.div(it, scalar) }
}
operator fun <T: Number> NDArray<T>.plusAssign(scalar: T) {
    map { operator.add(it, scalar) }
}
operator fun <T: Number> NDArray<T>.minusAssign(scalar: T) {
    map { operator.sub(it, scalar) }
}
operator fun <T: Number> NDArray<T>.timesAssign(scalar: T) {
    map { operator.mul(it, scalar) }
}

inline operator fun <reified T: Number> T.div(arr: NDArray<T>): NDArray<T> {
    val op = arr.operator
    return arr.flatMap { op.div(this, it) }
}
inline operator fun <reified T: Number> T.plus(arr: NDArray<T>): NDArray<T> {
    val op = arr.operator
    return arr.flatMap { op.add(this, it) }
}
inline operator fun <reified T: Number> T.minus(arr: NDArray<T>): NDArray<T> {
    val op = arr.operator
    return arr.flatMap { op.sub(this, it) }
}
inline operator fun <reified T: Number> T.times(arr: NDArray<T>): NDArray<T> {
    val op = arr.operator
    return arr.flatMap { op.mul(this, it) }
}

inline operator fun <reified T: Number> NDArray<T>.div(scalar: T): NDArray<T> {
    return flatMap { operator.div(it, scalar) }
}
inline operator fun <reified T: Number> NDArray<T>.plus(scalar: T): NDArray<T> {
    return flatMap { operator.add(it, scalar) }
}
inline operator fun <reified T: Number> NDArray<T>.minus(scalar: T): NDArray<T> {
    return flatMap { operator.sub(it, scalar) }
}
inline operator fun <reified T: Number> NDArray<T>.times(scalar: T): NDArray<T> {
    return flatMap { operator.mul(it, scalar) }
}

inline fun <reified T: Number> abs(arr: NDArray<T>) = arr.flatMap { arr.operator.abs(it) }
inline fun <reified T: Number> exp(arr: NDArray<T>) = arr.flatMap { arr.operator.exp(it) }
inline fun <reified T: Number> inv(arr: NDArray<T>) = arr.flatMap { arr.operator.inv(it) }
inline fun <reified T: Number> log(arr: NDArray<T>) = arr.flatMap { arr.operator.log(it) }
inline fun <reified T: Number> neg(arr: NDArray<T>) = arr.flatMap { arr.operator.neg(it) }
inline fun <reified T: Number> sqrt(arr: NDArray<T>) = arr.flatMap { arr.operator.sqrt(it) }
inline fun <reified T: Number> pow(arr: NDArray<T>, power: Int = 2) = arr.flatMap { arr.operator.pow(it, power) }

fun <T: Number> min(arr: NDArray<T>): T = arr.fold(arr[0,0]) { acc, t -> arr.operator.min(acc, t) }
fun <T: Number> max(arr: NDArray<T>): T = arr.fold(arr[0,0]) { acc, t -> arr.operator.max(acc, t) }

fun <T: Number> absAssign(arr: NDArray<T>) = arr.map { arr.operator.abs(it) }
fun <T: Number> expAssign(arr: NDArray<T>) = arr.map { arr.operator.exp(it) }
fun <T: Number> invAssign(arr: NDArray<T>) = arr.map { arr.operator.inv(it) }
fun <T: Number> logAssign(arr: NDArray<T>) = arr.map { arr.operator.log(it) }
fun <T: Number> negAssign(arr: NDArray<T>) = arr.map { arr.operator.neg(it) }
fun <T: Number> sqrtAssign(arr: NDArray<T>) = arr.map { arr.operator.sqrt(it) }
fun <T: Number> powAssign(arr: NDArray<T>, power: Int = 2) = arr.map { arr.operator.pow(it, power) }
