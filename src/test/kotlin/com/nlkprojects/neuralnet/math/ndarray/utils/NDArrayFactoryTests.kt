package com.nlkprojects.neuralnet.math.ndarray.utils


import com.nlkprojects.neuralnet.math.matrix.DoubleMatrix
import com.nlkprojects.neuralnet.math.matrix.FloatMatrix
import com.nlkprojects.neuralnet.math.ndarray.Axis
import com.nlkprojects.neuralnet.math.vector.DoubleVector
import com.nlkprojects.neuralnet.math.vector.FloatVector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFails

object NDArrayFactoryTests : Spek({
    describe("NDArray factory for vectors") {
        it("should create a vector on 'Y' axis") {
            assertEquals(vectorOf(0f, 1, Axis.Y).axis, Axis.Y)
        }
        it("should create vector with inference using `vectorOf`") {
            assertEquals(vectorOf(3f, 2)::class, FloatVector::class)
            assertEquals(vectorOf(1.0, 2)::class, DoubleVector::class)
            assertEquals(vectorOf(1f, 2f, 3f).toString(), "(1, 2, 3)")
            assertEquals(vectorOf<Float>(5, 0).toString(), "(0, 0, 0, 0, 0)")
            assertEquals(vectorOf(4, init = { 4f}).toString(), "(4, 4, 4, 4)")
        }
        it("should fail to create vector when column bigger than one") {
            assertFails { vectorOf<Float>(4,2) }
        }
        it("should fail to create vector when dimensions are negative") {
            assertFails { vectorOf<Float>(-4,-2) }
        }
        it("should fail to create int vector as it is not currently supported") {
            assertFails { vectorOf(4,3,2,1) }
        }
    }
    describe("NDArray factory for matrices") {
        it("should create matrix with inference using `matrixOf`") {
            assertEquals(matrixOf<Float>(3, 2)::class, FloatMatrix::class)
            assertEquals(matrixOf<Double>(2, 2)::class, DoubleMatrix::class)
            assertEquals(matrixOf(1f, 2, 2).errorString(), "[[1, 1][1, 1]]")
            assertEquals(matrixOf(5.0, 3, 1).errorString(), "[[5][5][5]]")
            assertEquals(matrixOf(1, 2, init = { _, _ -> 4f}).errorString(), "[[4, 4]]")
        }
        it("should fail to create matrix when dimensions are negative") {
            assertFails { matrixOf(-4, -2, 3f, 4f) }
            assertFails { matrixOf(-1,  2, 3f, 4f) }
            assertFails { matrixOf( 4, -3, 3f, 4f) }
        }
        it("should fail to create int matrix as it is not currently supported") {
            assertFails { matrixOf(4,3,2,1) }
        }
    }
    describe("NDArray factory from another array") {
        it("should create a new array with entries set to zero") {
            val v = vectorOf(3f, 5f, 3f)
            val m = matrixOf(2, 2, 4f, 2f, 5f, 2f)
            assertEquals(ndarrayFrom(v), vectorOf(0f, 0f, 0f))
            assertEquals(ndarrayFrom(m), matrixOf(2, 2, 0f, 0f, 0f, 0f))
        }
    }
})