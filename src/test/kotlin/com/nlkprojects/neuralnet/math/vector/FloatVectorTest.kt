package com.nlkprojects.neuralnet.math.vector

import kotlin.test.assertTrue
import kotlin.test.assertFails
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.vector.api.dot
import com.nlkprojects.neuralnet.math.vector.api.min
import com.nlkprojects.neuralnet.math.vector.api.max
import com.nlkprojects.neuralnet.math.ndarray.utils.*
import org.spekframework.spek2.style.specification.describe

object FloatVectorTest : Spek({

    val EPS = .00001f

    describe("Float Vector init") {
        it("should create a float vector when `constructor(size: Int)` called") {
            assertEquals(FloatVector(6).size, 6)
        }
        it("should create new vector when `constructor(vararg values: Flat)` called") {
            assertEquals(FloatVector(12.2f, 3.1f, 2f, 9f).toList(), listOf(12.2f, 3.1f, 2f, 9f))
        }
        it("should create new vector when `constructor(size: Int, value: Float)` called") {
            assertEquals(FloatVector(7, 2.1f).toList(), listOf(2.1f, 2.1f, 2.1f, 2.1f, 2.1f, 2.1f, 2.1f))
        }
        it("should create new vector when `constructor(size: Int, init: (Int) -> Float)` called") {
            assertEquals(FloatVector(3) { it * .5f }.toList(), listOf(0.0f, .5f, 1f))
        }
        it("should create a vector when `constructor(size: Int, axis: Axis = Axis.X, init: (Int) -> Float)` called") {
            val v = FloatVector(2, Axis.Y) { .0f }
            assertEquals(v.axis, Axis.Y)
        }
    }
    describe("Vector arithmetics with scalars") {
        val v = FloatVector(2f, 1.2f, 4f, 7.5f)
        it("should add '3' to every element") {
            assertEquals(3 + v, vectorOf(5f, 4.2f, 7f, 10.5f))
            assertEquals(v + 3, vectorOf(5f, 4.2f, 7f, 10.5f))
        }
        it("should divide every element by '3'") {
            assertTrue { equals((3/v), vectorOf(1.5f, 2.5f, 0.75f, 0.4f), EPS) }
            assertTrue { equals((v/3), vectorOf(.66666f, .4f, 1.33333f, 2.5f), EPS) }
        }
        it("should subtract '3' to every element") {
            assertEquals(3 - v, vectorOf(1f, 1.8f, -1f, -4.5f))
            assertEquals(v - 3, vectorOf(-1f, -1.8f, 1f, 4.5f))
        }
        it("should multiply every element by '3'") {
            assertTrue { equals((3 * v), vectorOf(6f, 3.6f, 12f, 22.5f), EPS) }
            assertTrue { equals((v * 3), vectorOf(6f, 3.6f, 12f, 22.5f), EPS) }
        }
    }

    describe("finding the min and max") {
        val lhs = vectorOf( 1.2f, .35f, 6.2f, 6.45f)
        val rhs = vectorOf(11.2f, .11f, 3.7f, 2.45f)

        mapOf(.35f to lhs, .11f to rhs).forEach { (expect, vector) ->
            it("min should be $expect in $vector") {
                assertEquals(expect, min(vector))
            }
        }
        mapOf(6.45f to lhs, 11.2f to rhs).forEach { (expect, vector) ->
            it("max should be $expect in $vector") {
                assertEquals(expect, max(vector))
            }
        }

        it("min values in $lhs and $rhs should be ( 1.2, .11, 3.7, 2.45)") {
            assertEquals(min(lhs, rhs), vectorOf(1.2f, .11f, 3.7f, 2.45f))
        }
        it("max values in $lhs and $rhs should be (11.2, .35, 6.2, 6.45)") {
            assertEquals(max(lhs, rhs), vectorOf(11.2f, .35f, 6.2f, 6.45f))
        }
    }

    describe("dot product between a vector and list") {
        val l = listOf(2f, 1f, 3f)
        val v = vectorOf(1.2f, .35f, 6.2f)
        val lErr = listOf(2f, 1f)

        it("should fail when computing the dot product of $v and $lErr") {
            assertFails { dot(v, lErr) }
        }
        it("should return '21.35' when after computing the dot product of $v and $l") {
            assertTrue { v.operator.equals(21.35f, dot(v, l), EPS) }
        }
    }
})
