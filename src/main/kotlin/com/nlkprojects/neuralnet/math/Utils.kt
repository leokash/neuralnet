package com.nlkprojects.neuralnet.math

import kotlin.math.ln
import java.math.RoundingMode
import java.text.DecimalFormat

private val fmt = DecimalFormat()
fun Number.stringify(decimals: Int = 2, rounding: RoundingMode = RoundingMode.HALF_EVEN): String {
    fmt.roundingMode = rounding
    fmt.applyPattern("#.".padEnd(2 + decimals, '#'))

    return fmt.format(this)
}

fun safeLog(value: Double, eps: Double = 1e-08): Double {
    return ln(if(value >= eps) value else eps)
}