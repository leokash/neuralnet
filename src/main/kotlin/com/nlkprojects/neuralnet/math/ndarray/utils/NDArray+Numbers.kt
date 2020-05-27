package com.nlkprojects.neuralnet.math.ndarray.utils

import com.nlkprojects.neuralnet.math.ndarray.NDArray

inline operator fun <reified T: Number> Int.divAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.plusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.minusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.timesAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Int.div(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.plus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.minus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Int.times(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Long.divAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.plusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.minusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.timesAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Long.div(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.plus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.minus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Long.times(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Float.divAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.plusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.minusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.timesAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Float.div(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.plus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.minus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Float.times(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Double.divAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.plusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.minusAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.timesAssign(array: NDArray<T>) {
    val op = array.operator
    array.map { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> Double.div(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.div(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.plus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.add(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.minus(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.sub(op.map(this), num) }
}
inline operator fun <reified T: Number> Double.times(array: NDArray<T>): NDArray<T> {
    val op = array.operator
    return array.flatMap { num -> op.mul(op.map(this), num) }
}

inline operator fun <reified T: Number> NDArray<T>.divAssign(scalar: Int) {
    map { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plusAssign(scalar: Int) {
    map { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minusAssign(scalar: Int) {
    map { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.timesAssign(scalar: Int) {
    map { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.div(scalar: Int): NDArray<T> {
    return flatMap { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plus(scalar: Int): NDArray<T> {
    return flatMap { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minus(scalar: Int): NDArray<T> {
    return flatMap { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.times(scalar: Int): NDArray<T> {
    return flatMap { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.divAssign(scalar: Long) {
    map { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plusAssign(scalar: Long) {
    map { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minusAssign(scalar: Long) {
    map { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.timesAssign(scalar: Long) {
    map { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.div(scalar: Long): NDArray<T> {
    return flatMap { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plus(scalar: Long): NDArray<T> {
    return flatMap { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minus(scalar: Long): NDArray<T> {
    return flatMap { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.times(scalar: Long): NDArray<T> {
    return flatMap { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.divAssign(scalar: Float) {
    map { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plusAssign(scalar: Float) {
    map { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minusAssign(scalar: Float) {
    map { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.timesAssign(scalar: Float) {
    map { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.div(scalar: Float): NDArray<T> {
    return flatMap { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plus(scalar: Float): NDArray<T> {
    return flatMap { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minus(scalar: Float): NDArray<T> {
    return flatMap { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.times(scalar: Float): NDArray<T> {
    return flatMap { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.divAssign(scalar: Double) {
    map { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plusAssign(scalar: Double) {
    map { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minusAssign(scalar: Double) {
    map { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.timesAssign(scalar: Double) {
    map { operator.mul(it, operator.map(scalar)) }
}

inline operator fun <reified T: Number> NDArray<T>.div(scalar: Double): NDArray<T> {
    return flatMap { operator.div(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.plus(scalar: Double): NDArray<T> {
    return flatMap { operator.add(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.minus(scalar: Double): NDArray<T> {
    return flatMap { operator.sub(it, operator.map(scalar)) }
}
inline operator fun <reified T: Number> NDArray<T>.times(scalar: Double): NDArray<T> {
    return flatMap { operator.mul(it, operator.map(scalar)) }
}
