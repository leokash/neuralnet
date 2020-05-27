package com.nlkprojects.neuralnet.math.operator

import com.nlkprojects.neuralnet.math.operator.api.Operator
import kotlin.math.pow
import kotlinx.serialization.Serializable
import com.nlkprojects.neuralnet.math.safeLog

@Serializable
object FloatOperator: Operator<Float> {
    override val one: Float get()  = 1f
    override val zero: Float get() = 0f

    override fun abs(value: Float): Float = kotlin.math.abs(value)
    override fun exp(value: Float): Float = kotlin.math.exp(value)
    override fun inv(value: Float): Float = if (value == 0f) 0f else 1f / value
    override fun log(value: Float): Float = safeLog(value.toDouble()).toFloat()
    override fun neg(value: Float): Float = -value
    override fun sqrt(value: Float): Float = kotlin.math.sqrt(value)

    override fun pow(value: Float, power: Int): Float = value.pow(power)

    override fun eq(lhs: Float, rhs: Float): Boolean = lhs == rhs
    override fun compare(lhs: Float, rhs: Float): Int = lhs.compareTo(rhs)

    override fun min(lhs: Float, rhs: Float): Float = kotlin.math.min(lhs, rhs)
    override fun max(lhs: Float, rhs: Float): Float = kotlin.math.max(lhs, rhs)

    override fun map(value: Int): Float = value.toFloat()
    override fun map(value: Long): Float = value.toFloat()
    override fun map(value: Float): Float = value
    override fun map(value: Double): Float = value.toFloat()

    override fun add(lhs: Float, rhs: Float): Float = lhs + rhs
    override fun mul(lhs: Float, rhs: Float): Float = lhs * rhs
}
