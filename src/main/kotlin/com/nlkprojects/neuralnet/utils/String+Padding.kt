package com.nlkprojects.neuralnet.utils

import kotlin.math.abs

fun String.pad(max: Int = 0) : String {
    if (max == 0 || this.length >= max)
        return this

    val sPadding = (max - this.length) / 2
    val ePadding = if (max % 2 != this.length % 2) sPadding + 1 else sPadding
    return "".padStart(sPadding) + this + "".padStart(ePadding)
}
fun String.padLeft( max: Int = 0) : String {
    if (max == 0 || this.length >= max)
        return this

    return "".padStart(abs(this.length - max)) + this
}
fun String.padRight( max: Int = 0) : String {
    if (max == 0 || this.length >= max)
        return this

    return this + "".padStart(abs(this.length - max))
}
