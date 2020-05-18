
object Libraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerialization}"

    object Test {
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

        const val spekDsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        const val spekJUnit = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
    }
}
