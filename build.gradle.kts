
@file:Suppress("UnstableApiUsage")

buildscript {
    repositories {
        jcenter()
    }

    dependencies {

    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/spekframework/spek")
    }
}

group   = "com.nlkprojects.neuralnet"
version = "0.0.1"

val appGroup: String      = group.toString()
val appVersion: String    = version.toString()
val fileSeparator: String = File.separator
