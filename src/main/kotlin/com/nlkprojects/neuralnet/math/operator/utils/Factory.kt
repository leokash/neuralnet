package com.nlkprojects.neuralnet.math.operator.utils

import kotlin.reflect.KClass
import java.lang.RuntimeException
import com.nlkprojects.neuralnet.math.operator.*
import com.nlkprojects.neuralnet.math.operator.api.Operator

inline fun <reified T: Number> operator(): Operator<out T> {
    return operatorOf(T::class)
}
fun <T: Number> operatorOf(type: KClass<T>): Operator<out T> {
    @Suppress("UNCHECKED_CAST")
    return when (type) {
        Float::class  -> FloatOperator
        Double::class -> DoubleOperator

        else -> throw RuntimeException("Unsupported operator for type: $type")
    } as Operator<T>
}
