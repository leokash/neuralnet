package com.nlkprojects.neuralnet.math.operator

import com.nlkprojects.neuralnet.math.operator.api.Operator
import kotlin.math.pow
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.safeLog

@Serializable
object DoubleOperator: Operator<Double> {
    override val one: Double get()  = 1.0
    override val zero: Double get() = 0.0

    override fun abs(value: Double): Double = kotlin.math.abs(value)
    override fun exp(value: Double): Double = kotlin.math.exp(value)
    override fun inv(value: Double): Double = if (value == .0) .0 else 1.0 / value
    override fun log(value: Double): Double = safeLog(value)
    override fun neg(value: Double): Double = -value
    override fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

    override fun pow(value: Double, power: Int): Double = value.pow(power)

    override fun eq(lhs: Double, rhs: Double): Boolean = lhs == rhs
    override fun compare(lhs: Double, rhs: Double): Int = lhs.compareTo(rhs)

    override fun min(lhs: Double, rhs: Double): Double = kotlin.math.min(lhs, rhs)
    override fun max(lhs: Double, rhs: Double): Double = kotlin.math.max(lhs, rhs)

    override fun map(value: Int): Double = value.toDouble()
    override fun map(value: Long): Double = value.toDouble()
    override fun map(value: Float): Double = value.toDouble()
    override fun map(value: Double): Double = value

    override fun add(lhs: Double, rhs: Double): Double = lhs + rhs
    override fun mul(lhs: Double, rhs: Double): Double = lhs * rhs
}
