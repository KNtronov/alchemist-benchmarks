import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.multiJvmTesting) // Pre-configures the Java toolchains
    alias(libs.plugins.taskTree) // Helps debugging dependencies among gradle tasks
    alias(libs.plugins.shadowJar)
    id("me.champeau.jmh") version "0.7.1"
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    // Check the catalog at gradle/libs.versions.gradle
    jmhImplementation(libs.bundles.alchemist)
    //jmhImplementation(alchemist("api"))
    //jmhImplementation(alchemist("engine"))
    //jmhImplementation(alchemist("loading"))
    //jmhImplementation(alchemist("euclidean-geometry"))
    //jmhImplementation(alchemist("implementationbase"))
    //jmhImplementation(alchemist("incarnation-protelis"))
    //jmhImplementation(alchemist("swingui"))
    jmhImplementation("org.openjdk.jmh:jmh-core:1.36")
    jmhImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.36")
    jmhImplementation(project(mapOf("path" to ":")))
    jmh("org.apache.commons:commons-math3:3.6.1")
    implementation(kotlin("stdlib-jdk8"))
}

multiJvm {
    jvmVersionForCompilation.set(latestJava)
}

tasks.withType<Tar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}
tasks.withType<Zip>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}
tasks.withType<ShadowJar>().configureEach {
    isZip64 = true
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}