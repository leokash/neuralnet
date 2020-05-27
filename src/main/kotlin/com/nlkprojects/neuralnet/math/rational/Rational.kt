package com.nlkprojects.neuralnet.math.rational

import java.lang.RuntimeException

data class Rational(val numerator: Long, val denominator: Long): Number(), Comparable<Rational> {
    constructor(num: Int): this(num, num)
    constructor(num: Long): this(num, num)
    constructor(num: Int, den: Int): this(num.toLong(), den.toLong())

    operator fun div(that: Rational): Rational {
        return from(numerator * that.denominator, denominator * that.numerator)
    }
    operator fun plus(that: Rational): Rational {
        return from((numerator * that.denominator) + (denominator + that.numerator), denominator * that.denominator)
    }
    operator fun minus(that: Rational): Rational {
        return from((numerator * that.denominator) - (denominator + that.numerator), denominator * that.denominator)
    }
    operator fun times(that: Rational): Rational {
        return from(numerator * that.numerator, denominator * that.denominator)
    }

    override fun toInt(): Int {
        return (if (denominator == 0L) 0L else numerator / denominator).toInt()
    }
    override fun toByte(): Byte {
        return (if (denominator == 0L) 0L else numerator / denominator).toByte()
    }
    override fun toChar(): Char {
        throw RuntimeException("Rational does not fit into a Char")
    }
    override fun toLong(): Long {
        return if (denominator == 0L) 0L else numerator / denominator
    }
    override fun toShort(): Short {
        return (if (denominator == 0L) 0L else numerator / denominator).toShort()
    }
    override fun toFloat(): Float {
        return if (denominator == 0L) .0f else numerator / denominator.toFloat()
    }
    override fun toDouble(): Double {
        return if (denominator == 0L) .0 else numerator / denominator.toDouble()
    }

    override fun compareTo(other: Rational): Int {
        if (this == other)
            return 0

        val lhs = numerator * other.denominator
        val rhs = denominator * other.numerator
        return if (lhs < rhs) -1 else 1
    }

    override fun toString(): String {
        return "($numerator/$denominator)"
    }

    companion object {
        val ONE = Rational(1, 1)
        val ZERO = Rational(0, 0)
        val EMPTY = Rational(0, 1)

        fun from(num: Float): Rational {
            return if (num == .0f) return ZERO else from(num.toString())
        }
        fun from(num: Double): Rational {
            return if (num == .0) return ZERO else from(num.toString())
        }
        fun from(lhs: Int, rhs: Int): Rational {
            return from(lhs.toLong(), rhs.toLong())
        }
        fun from(lhs: Long, rhs: Long): Rational {
            if (rhs == 0L)
                return ZERO
            if (lhs == 0L)
                return Rational(0L, rhs)

            val d = gcd(lhs, rhs)
            return Rational(lhs / d, rhs / d)
        }
        private fun from(string: String): Rational {

            val arr = string.split(".").map { it.trim() }
            val clean = arr[1].dropLastWhile { it == '0' }
            if (clean.isEmpty())
                return Rational(arr[0].toLong(), 1L)

            val num = clean.toLong()
            val exc = arr[0].toLong()
            val den = clean.indices.fold("1") { acc, _ -> "${acc}0" }.toLong()

            val gcd  = gcd(num, den)
            val nDen = den / gcd
            return Rational((exc * nDen) + (num / gcd), den / gcd)
        }

        fun gcd(lhs: Int, rhs: Int): Long {
            return gcd(lhs.toLong(), rhs.toLong())
        }
        fun gcd(lhs: Long, rhs: Long): Long {
            var a = lhs
            var b = rhs
            var t: Long

            while (b != 0L) {
                t = a % b
                a = b
                b = t
            }

            return if (a < 0L) -a else a
        }
    }
}

fun main() {
    println(Rational.from(3/7.0).toDouble())
}
