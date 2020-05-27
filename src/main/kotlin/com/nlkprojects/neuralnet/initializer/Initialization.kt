package com.nlkprojects.neuralnet.initializer


sealed class Initialization {
    object HeNormal: Initialization()
    object HeUniform: Initialization()
    object LeCunNormal: Initialization()
    object LeCunUniform: Initialization()
    object XavierNormal: Initialization()
    object XavierUniform: Initialization()
    data class Constant<T: Number>(val value: T): Initialization()
    data class FixedRange<T: Number>(val radius: T, val usingPseudoRandom: Boolean = true): Initialization()
    data class MinMax<T: Number>(val min: T, val max: T, val usingPseudoRandom: Boolean = true): Initialization()
    data class GaussianDistributed<T: Number>(val variance: T, val usingPseudoRandom: Boolean = true): Initialization()
}
