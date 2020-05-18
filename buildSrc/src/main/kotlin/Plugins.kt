
object Plugins {
    const val kotlin_serialization = "org.jetbrains.kotlin.plugin.serialization"
    object BuildScript {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }
}
