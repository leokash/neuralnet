package com.nlkprojects.neuralnet.math.operator.api

interface Operator<T: Number> {
    val one: T
    val zero: T

    fun abs(value: T): T
    fun exp(value: T): T
    fun inv(value: T): T
    fun log(value: T): T
    fun neg(value: T): T
    fun sqrt(value: T): T
    fun pow(value: T, power: Int = 2): T

    fun min(lhs: T, rhs: T): T
    fun max(lhs: T, rhs: T): T

    fun eq(lhs: T, rhs: T): Boolean
    fun neq(lhs: T, rhs: T): Boolean = !eq(lhs, rhs)

    fun compare(lhs: T, rhs: T): Int
    fun equals(lhs: T, rhs: T, tolerance: T): Boolean {
        val lower = sub(rhs, tolerance)
        val upper = add(rhs, tolerance)

        return compare(lhs, lower) >= 0 && 0 <= compare(upper, lhs)
    }

    //Map: conversion to T
    fun map(value: Int): T
    fun map(value: Long): T
    fun map(value: Float): T
    fun map(value: Double): T

    fun add(lhs: T, rhs: T): T
    fun mul(lhs: T, rhs: T): T
    fun sub(lhs: T, rhs: T): T = add(lhs, neg(rhs))
    fun div(lhs: T, rhs: T): T = mul(lhs, inv(rhs))
}
