
@file:Suppress(
    "UnstableApiUsage",
    "SuspiciousCollectionReassignment"
)

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath(Plugins.BuildScript.kotlinGradlePlugin)
        classpath(Plugins.BuildScript.kotlinSerializationPlugin)
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

group = "com.com.nlkprojects.neuralnet"
version = "0.0.1"

val appGroup: String   = group.toString()
val appVersion: String = version.toString()

tasks {
    jar {
        manifest {
            attributes["Implementation-Title"]   = project.name.capitalize()
            attributes["Implementation-Vendor"]  = "Kash Kabeya"
            attributes["Implementation-Version"] = appVersion
        }
    }
    test {
        useJUnitPlatform()
    }
    clean {
        delete("build")
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            sourceCompatibility = "11.0"
            freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            sourceCompatibility = "11.0"
            freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
        }
    }
}

plugins {
    kotlin("jvm") version Versions.kotlin
    id(Plugins.kotlin_serialization) version Versions.kotlin //Needs to match the version of kotlin's stdlib...
}

val sourceJar = task("sourceJar", type = Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.kotlinSerialization)

    testImplementation(Libraries.Test.spekDsl)
    testImplementation(Libraries.Test.spekJUnit)
    testImplementation(Libraries.Test.kotlinTest)
    testImplementation(Libraries.Test.kotlinReflect)
}
