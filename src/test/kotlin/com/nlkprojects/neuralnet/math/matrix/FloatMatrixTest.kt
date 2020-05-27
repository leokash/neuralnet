package com.nlkprojects.neuralnet.math.matrix

import kotlin.test.assertTrue
import kotlin.test.assertFails
import org.spekframework.spek2.Spek
import com.nlkprojects.neuralnet.math.ndarray.utils.equals
import com.nlkprojects.neuralnet.math.ndarray.utils.matrixOf
import org.spekframework.spek2.style.specification.describe

object FloatMatrixTest : Spek({
    val EPS = .000001f
    val lhs = FloatMatrix(2, 4, 1f, 1f, 1f, 2.1f, 3.5f, 2.1f, 5f, 3f)
    val rhs = FloatMatrix(2, 4, 1f, 2f, 3f, 4.9f, 1.2f, 6.7f, 5f, 8f)
    val err = FloatMatrix(3, 4, 1f, 2f, 3f, 4.9f, 1.2f, 6.7f, 5f, 8f, 2f, 3f, 4f, 1f)

    describe("transposition") {
        it("should return [[1, 3.5][1, 2.1][1, 5][2.1, 3]] when ${lhs.errorString()} is transposed") {
            val res = matrixOf(4, 2, 1f, 3.5f, 1f, 2.1f, 1f, 5f, 2.1f, 3f)
            assertTrue { equals(lhs.T, res, EPS) }

        }
    }
    describe("Matrix arithmetics") {
        describe("additions") {
            it("should fail when adding matrices of different shapes") {
                assertFails {
                    lhs + err
                }
            }
            it("should return [[2,3,4,7][4.7,8.8,10,11]] when ${lhs.errorString()} + ${rhs.errorString()}") {
                val res = FloatMatrix(2, 4, 2f, 3f, 4f, 7f, 4.7f, 8.8f, 10f, 11f)
                assertTrue { equals((lhs + rhs), res, EPS) }
                assertTrue { equals((rhs + lhs), res, EPS) }
            }
        }
        describe("subtractions") {
            it("should fail when subtracting matrices of different shapes") {
                assertFails {
                    lhs - err
                }
            }
            it("should return [[0,-1,-2,-2.8][2.3,-4.6,0,-5]] when ${lhs.errorString()} - ${rhs.errorString()}") {
                val res = FloatMatrix(2, 4, 0f, -1f, -2f, -2.8f, 2.3f, -4.6f, 0f, -5f)
                assertTrue { equals((lhs - rhs), res, EPS) }
            }
            it("should return [[0,1,2,2.8][-2.3,4.6,0,5]] when ${rhs.errorString()} - ${lhs.errorString()}") {
                val res = FloatMatrix(2, 4, 0f, 1f, 2f, 2.8f, -2.3f, 4.6f, 0f, 5f)
                assertTrue { equals((rhs - lhs), res, EPS) }
            }
        }
        describe("multiplication") {
            it("should fail when multiplying ${lhs.errorString()} with ${err.errorString()}") {
                assertFails {
                    lhs * err
                }
            }
            it("should fail when multiplying ${lhs.errorString()} with ${rhs.errorString()}") {
                assertFails {
                    lhs * rhs
                }
            }
            it("should return 2x2 matrix when ${lhs.errorString()} * ${rhs.T.errorString()}") {
                val res = FloatMatrix(2, 2, 16.29f, 29.7f, 37.4f, 67.27f)
                assertTrue { equals((lhs * rhs.T), res, EPS) }
            }
            it("should return 4x4 matrix when ${lhs.T.errorString()} * ${rhs.errorString()}") {
                val res = FloatMatrix(4, 4,
                        5.2f,  25.45f, 20.5f, 32.9f,
                        3.52f, 16.07f, 13.5f, 21.7f,
                        7f,    35.5f,  28f,   44.9f,
                        5.7f,  24.3f,  21.3f, 34.29f)
                assertTrue { equals((lhs.T * rhs), res, EPS) }
            }
        }
    }
})
