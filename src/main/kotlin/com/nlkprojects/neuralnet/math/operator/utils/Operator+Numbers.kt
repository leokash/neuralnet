package com.nlkprojects.neuralnet.math.operator.utils

import com.nlkprojects.neuralnet.math.operator.api.Operator

fun <T: Number> Operator<T>.addm(lhs: T, rhs: Int) = add(lhs, map(rhs))
fun <T: Number> Operator<T>.subm(lhs: T, rhs: Int) = sub(lhs, map(rhs))
fun <T: Number> Operator<T>.mulm(lhs: T, rhs: Int) = mul(lhs, map(rhs))
fun <T: Number> Operator<T>.divm(lhs: T, rhs: Int) = div(lhs, map(rhs))

fun <T: Number> Operator<T>.addm(lhs: Int, rhs: T) = add(map(lhs), rhs)
fun <T: Number> Operator<T>.subm(lhs: Int, rhs: T) = sub(map(lhs), rhs)
fun <T: Number> Operator<T>.mulm(lhs: Int, rhs: T) = mul(map(lhs), rhs)
fun <T: Number> Operator<T>.divm(lhs: Int, rhs: T) = div(map(lhs), rhs)

fun <T: Number> Operator<T>.addm(lhs: T, rhs: Long) = add(lhs, map(rhs))
fun <T: Number> Operator<T>.subm(lhs: T, rhs: Long) = sub(lhs, map(rhs))
fun <T: Number> Operator<T>.mulm(lhs: T, rhs: Long) = mul(lhs, map(rhs))
fun <T: Number> Operator<T>.divm(lhs: T, rhs: Long) = div(lhs, map(rhs))

fun <T: Number> Operator<T>.addm(lhs: Long, rhs: T) = add(map(lhs), rhs)
fun <T: Number> Operator<T>.subm(lhs: Long, rhs: T) = sub(map(lhs), rhs)
fun <T: Number> Operator<T>.mulm(lhs: Long, rhs: T) = mul(map(lhs), rhs)
fun <T: Number> Operator<T>.divm(lhs: Long, rhs: T) = div(map(lhs), rhs)

fun <T: Number> Operator<T>.addm(lhs: T, rhs: Float) = add(lhs, map(rhs))
fun <T: Number> Operator<T>.subm(lhs: T, rhs: Float) = sub(lhs, map(rhs))
fun <T: Number> Operator<T>.mulm(lhs: T, rhs: Float) = mul(lhs, map(rhs))
fun <T: Number> Operator<T>.divm(lhs: T, rhs: Float) = div(lhs, map(rhs))

fun <T: Number> Operator<T>.addm(lhs: Float, rhs: T) = add(map(lhs), rhs)
fun <T: Number> Operator<T>.subm(lhs: Float, rhs: T) = sub(map(lhs), rhs)
fun <T: Number> Operator<T>.mulm(lhs: Float, rhs: T) = mul(map(lhs), rhs)
fun <T: Number> Operator<T>.divm(lhs: Float, rhs: T) = div(map(lhs), rhs)

fun <T: Number> Operator<T>.addm(lhs: T, rhs: Double) = add(lhs, map(rhs))
fun <T: Number> Operator<T>.subm(lhs: T, rhs: Double) = sub(lhs, map(rhs))
fun <T: Number> Operator<T>.mulm(lhs: T, rhs: Double) = mul(lhs, map(rhs))
fun <T: Number> Operator<T>.divm(lhs: T, rhs: Double) = div(lhs, map(rhs))

fun <T: Number> Operator<T>.addm(lhs: Double, rhs: T) = add(map(lhs), rhs)
fun <T: Number> Operator<T>.subm(lhs: Double, rhs: T) = sub(map(lhs), rhs)
fun <T: Number> Operator<T>.mulm(lhs: Double, rhs: T) = mul(map(lhs), rhs)
fun <T: Number> Operator<T>.divm(lhs: Double, rhs: T) = div(map(lhs), rhs)
