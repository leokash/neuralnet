
package com.nlkprojects.neuralnet.initializer


import kotlin.test.assertTrue
import kotlin.test.assertEquals
import org.spekframework.spek2.Spek
import com.nlkprojects.neuralnet.math.matrix.FloatMatrix
import com.nlkprojects.neuralnet.math.ndarray.utils.toSet
import org.spekframework.spek2.style.specification.describe
import com.nlkprojects.neuralnet.math.operator.FloatOperator
import com.nlkprojects.neuralnet.initializer.api.random.MinMaxGenerator

object RandomInitializerSpec : Spek({
    describe("Random Initializer tester") {
        val rand = RandomInitializer(MinMaxGenerator(1f, 2f, FloatOperator))
        it("should generate unique floats") {
            val arr = FloatArray(1000) { rand.init() }
            assertEquals(arr.size, 1000)
        }
        it("should init a matrix with random values") {
            val m = FloatMatrix(5, 5)
            rand.init(m, 0)

            val set = m.toSet()
            assertEquals(set.size, 25)
            assertTrue { set.none { it == .0f } }
        }
        it("should still generate unique floats in non-pseudo mode") {
            val r = RandomInitializer(MinMaxGenerator(1f, 2f, FloatOperator, false))
            val arr = FloatArray(1000) { r.init() }
            assertEquals(arr.size, 1000)
        }
    }
})
