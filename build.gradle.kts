import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("plugin.serialization") version "2.2.0"
    application
    id("com.gradleup.shadow") version "8.3.1"
    kotlin("jvm") version "2.2.0"
}

group = "dev.crayson"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.3.1"
val jdaKtx = "0.12.0"
val lampVersion = "4.0.0-rc.10"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin coroutine dependency
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // JDA
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("club.minnced:jda-ktx:${jdaKtx}")

    // Commands
    implementation("io.github.revxrsal:lamp.common:$lampVersion")
    implementation("io.github.revxrsal:lamp.jda:$lampVersion")

    // LOGGING
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.18")

    // DATABASE
    // MongoDB Kotlin driver dependency
    api("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")

    // CONFIG
    implementation("de.exlll:configlib-yaml:4.6.1")

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-datetime
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass = "dev.crayson.DiscordBotKt"
}

tasks.withType<JavaCompile> {
    // Preserve parameter names in the bytecode
    options.compilerArgs.add("-parameters")
}

// optional: if you're using Kotlin
tasks.withType<KotlinJvmCompile> {
    compilerOptions {
        javaParameters = true
    }
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}