package com.nlkprojects.neuralnet.math.operator

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertTrue


object FloatOperatorTests: Spek({
    describe("Float arithmetic operations provider test") {
        val operator = FloatOperator
        it("should provide a ONE constant") { assertEquals(operator.one, 1f) }
        it("should provide a ZERO constant") { assertEquals(operator.zero, .0f) }
        it("should be able to add to floats") { assertEquals(operator.add(1f, 3f), 4f) }
        it("should be able to subtract to floats") { assertEquals(operator.sub(1f, 3f), -2f) }
        it("should be able to multiply to floats") { assertEquals(operator.mul(5f, 3f), 15f) }
        it("should be able to divide to floats") { assertEquals(operator.div(6f, 3f), 2f) }
        it("should be able to perform compound operations") {
            assertEquals(operator.add(5f, operator.mul(operator.div(24f, 2f), 3f)), 41f)
        }

        it("should be able to perform equality checks") { assertTrue { operator.eq(2f, 2f) } }
        it("should be able to perform inequality checks") { assertTrue { operator.neq(2f, 4f) } }
        it("should map ints to float") { assertTrue { operator.map(23)::class == Float::class } }
        it("should map longs to float") { assertTrue { operator.map(54L)::class == Float::class } }
        it("should map doubles to float") { assertTrue { operator.map(.23)::class == Float::class } }
    }
})
