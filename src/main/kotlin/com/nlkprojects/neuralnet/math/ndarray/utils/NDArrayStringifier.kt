package com.nlkprojects.neuralnet.math.ndarray.utils

import kotlin.math.max
import com.nlkprojects.neuralnet.utils.padLeft
import com.nlkprojects.neuralnet.math.stringify
import com.nlkprojects.neuralnet.math.ndarray.NDArray

class NDArrayStringifier {
    private class Spacer(var max: Int) {
        fun update(s: Int) {
            max = max(max, s)
        }
    }
    private class Padded(private val string: String, private val spacer: Spacer) {
        init {
            spacer.update(string.length)
        }
        override fun toString(): String {
            return string.padLeft(spacer.max)
        }
    }

    fun <T: Number> stringify(array: NDArray<T>): String {
        if (array.isVector)
            return stringifyVector(array)

        val spacers = mutableMapOf<Int, Spacer>()
        val stringsArr = mutableListOf<List<Padded>>()
        for (i in (0 until array.rows))
            stringsArr += array.row(i).mapIndexed { j, t -> Padded(t.stringify(5), spacers.getOrPut(j) { Spacer(0) }) }

        return stringsArr.joinToString (separator = "") { list ->
            list.joinToString (prefix = "|", postfix = "|\n", separator = " ") { it.toString() }
        }
    }
    fun <T: Number> errorString(array: NDArray<T>): String {
        if (array.isVector)
            return stringifyVector(array)

        val rows = array.rows
        return (0 until rows).joinToString (separator = "", prefix = "[", postfix = "]") { i ->
            array.row(i).joinToString (prefix = "[", postfix = "]") { it.stringify(5) }
        }
    }
    private fun <T: Number> stringifyVector(arr: NDArray<T>): String {
        return arr.row(0).joinToString (prefix = "(", postfix = ")") { it.stringify(5) }
    }
}
