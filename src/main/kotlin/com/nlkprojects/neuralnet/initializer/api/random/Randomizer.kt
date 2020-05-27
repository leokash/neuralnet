package com.nlkprojects.neuralnet.initializer.api.random

import java.util.*

object Randomizer {
    private val normal = Random() //true randomizer
    private val pseudo = Random(3) //needed for debugging... will return same random values on each run

    fun random(usingPseudoRandom: Boolean = true): Random {
        return if (usingPseudoRandom) pseudo else normal
    }
}
