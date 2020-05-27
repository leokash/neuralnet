package com.nlkprojects.neuralnet.utils

/**
 * Utility function needed to swallow unused return types.
 */
val <T> T.noop get() = Unit
