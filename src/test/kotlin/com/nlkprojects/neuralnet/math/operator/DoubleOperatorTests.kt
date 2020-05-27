package com.nlkprojects.neuralnet.math.operator

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object DoubleOperatorTests : Spek({

    describe("Double arithmetic operations provider test") {
        val operator = DoubleOperator
        it("should have a ONE constant") {
            assertEquals(operator.one, 1.0)
        }

        it("should have a ZERO constant") {
            assertEquals(operator.zero, .0)
        }

        it("should be able to perform `abs` operations") {
            assertEquals(operator.abs(-2.0), 2.0)
            assertEquals(operator.abs(23.0), 23.0)
        }

        it("should be able to perform `inv` operations") {
            assertEquals(operator.inv( .0),  .0)
            assertEquals(operator.inv(1.0), 1.0)
            assertEquals(operator.inv(2.0),  .5)
            assertEquals(operator.inv( .4), 2.5)
        }

        it("should be able to perform `neg` operations") {
            assertEquals(operator.neg(2.0), -2.0)
            assertEquals(operator.neg(-2.0), 2.0)
        }

        it("should be able to perform `pow` operations") {
            assertEquals(operator.pow(2.0, 4), 16.0)
        }

        it("should be able to perform `compare` operations") {
            assertEquals(operator.compare(2.0, 2.0),  0)
            assertEquals(operator.compare(1.0, 2.0), -1)
            assertEquals(operator.compare(3.0, 2.0),  1)
        }

        it("should be able to perform `min` operations") {
            assertEquals(operator.min( 2.0, 5.0), 2.0)
            assertEquals(operator.min(25.0, 5.0), 5.0)
        }

        it("should be able to perform `max` operations") {
            assertEquals(operator.max( 2.0, 5.0),  5.0)
            assertEquals(operator.max(25.0, 5.0), 25.0)
        }

        it("should be able to perform `map` operations") {
            assertEquals(operator.map(413), 413.0)
            assertEquals(operator.map( 3f),   3.0)
        }

        it("should be able to perform `add` operations") {
            assertEquals(operator.add(23.0,  7.0), 30.0)
            assertEquals(operator.add(12.0, -7.0),  5.0)
        }

        it("should be able to perform `mul` operations") {
            assertEquals(operator.mul( 3.0,  7.0),  21.0)
            assertEquals(operator.mul(-5.0,  2.0), -10.0)
        }
    }
})