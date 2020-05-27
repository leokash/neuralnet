package com.nlkprojects.neuralnet.math.vector

import kotlin.test.assertTrue
import kotlin.test.assertFails
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.vector.api.dot
import com.nlkprojects.neuralnet.math.ndarray.utils.*
import com.nlkprojects.neuralnet.math.matrix.DoubleMatrix
import org.spekframework.spek2.style.specification.describe

object DoubleVectorTests: Spek({
    describe("Vector init") {
        it("should create new vector when `constructor(size: Int)` called") {
            assertEquals(DoubleVector(12).size, 12)
        }
        it("should create new vector when `constructor(vararg values: Double)` called") {
            assertEquals(DoubleVector(12.2, 3.1, 2.0, 9.0).toList(), listOf(12.2, 3.1, 2.0, 9.0))
        }
        it("should create new vector when `constructor(size: Int, value: Double)` called") {
            assertEquals(DoubleVector(7, 2.1).toList(), listOf(2.1, 2.1, 2.1, 2.1, 2.1, 2.1, 2.1))
        }
        it("should create new vector when `constructor(size: Int, init: (Int) -> Double)` called") {
            assertEquals(DoubleVector(3) { it * 1.5 }.toList(), listOf(0.0, 1.5, 3.0))
        }
        it("should create a vector when `constructor(size: Int, axis: Axis = Axis.X, init: (Int) -> Double)` called") {
            val v = DoubleVector(2, Axis.Y) { 0.0 }
            assertEquals(v.axis, Axis.Y)
        }
    }
    describe("Vector arithmetics tests") {
        val lhs = DoubleVector(2.0, -3.0, 2.5)
        val rhs = DoubleVector(6.0,  1.0,  .5)
        val err = DoubleVector(6.0,  1.0,  .5, 3.0)

        val EPS = .000001

        describe("divisions") {
            it("should fail when / vectors of different sizes") {
                assertFails { lhs / err }
                assertFails { rhs / err }
            }
            it("should equal to (0.33333, -3, 5) when `lhs` / `rhs`") {
                assertTrue { equals((lhs/rhs), vectorOf(.333333, -3.0, 5.0), EPS) }
            }
            it("should equal to (3.0, -0.33333, 0.2) when `rhs` / `lhs`") {
                assertTrue { equals((rhs/lhs), vectorOf(3.0, -0.333333, 0.2), EPS) }
            }
        }
        describe("additions") {
            it("should equal to (8, -2, 3) when `lhs` + `rhs`") {
                assertEquals(lhs + rhs, vectorOf(8.0, -2.0, 3.0))
            }
            it("should equal to (8, -2, 3) when `rhs` + `lhs`") {
                assertEquals(rhs + lhs, vectorOf(8.0, -2.0, 3.0))
            }
            it("should fail when + vectors of different sizes") {
                assertFails { lhs + err }
                assertFails { rhs + err }
            }
        }
        describe("subtraction") {
            it("should fail when - vectors of different sizes") {
                assertFails { lhs - err }
                assertFails { rhs - err }
            }
            it("should equal to (-4, -4,  2) when `lhs` - `rhs`") {
                assertEquals(lhs - rhs, vectorOf(-4.0, -4.0, 2.0))
            }
            it("should equal to ( 4,  4, -2) when `rhs` - `lhs`") {
                assertEquals(rhs - lhs, vectorOf(4.0, 4.0, -2.0))
            }
        }
        describe("multiplication") {
            it("should fail when * vectors of different sizes") {
                assertFails { lhs * err }
                assertFails { rhs * err }
            }
            it("should equal to (12, -3,  1.25) when `lhs` - `rhs`") {
                assertEquals(lhs * rhs, vectorOf(12.0, -3.0, 1.25))
            }
            it("should equal to (12,  -3, 1.25) when `rhs` - `lhs`") {
                assertEquals(rhs * lhs, vectorOf(12.0, -3.0, 1.25))
            }
        }

        describe("dot product") {
            it("should fail when sizes do not match") {
                assertFails { dot(lhs, err) }
                assertFails { dot(err, rhs) }
            }
            it("should return '3.5' for the dot product of `lhs` and `7`") {
                assertEquals(dot(lhs, 7.0), 10.5)
            }
            it("should return '52.5' for the dot product of `rhs` and `7`") {
                assertEquals(dot(rhs, 7.0), 52.5)
            }
            it("should return '10.25' for the dot product of `lhs` and `rhs`") {
                assertEquals(dot(lhs, rhs), 10.25)
            }
            it("should return '10.25' for the dot product of `rhs` and `lhs`") {
                assertEquals(dot(rhs, lhs), 10.25)
            }
            it("should fail when attempting a dot product between a vector and a matrix") {
                val m = matrixOf(1, 2, 2.7, 3.1)
                assertFails { dot(lhs, m) }
            }
            it("should return (20.4) when attempting a dot product between a vector and a matrix") {
                val m = matrixOf(3, 1, 2.7, 3.1, 2.2)
                assertTrue { equals(dot(rhs, m), vectorOf(20.4), EPS) }
            }
            it("should return (20.4, 14.65) when attempting a dot product between a vector and a matrix") {
                val m = matrixOf(2, 3, 2.7, 3.1, 2.2, 1.4, 5.0, 2.5)
                assertTrue { equals(dot(rhs, m), vectorOf(20.4, 14.65), EPS) }
            }
        }

        describe("outer Product") {
            it("should return a matrix when (2) ⊗ lhs") {
                val m = vectorOf(2.0).outerProduct(lhs)
                assertTrue { m::class == DoubleMatrix::class }
                assertEquals(m, matrixOf(1, 3, 4.0, -6.0, 5.0))
            }

            it("should return a matrix with 2 rows and 3 columns when (2, 3) ⊗ lhs") {
                val m = vectorOf(2.0, 3.0).outerProduct(lhs)
                assertEquals(m.rows, 2)
                assertEquals(m.columns, 3)
            }
        }

    }
})
