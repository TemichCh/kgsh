import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val ktorVersion = "1.6.0"//"1.4.2"
val serializationVersion = "1.2.1" //"1.0.0-RC"
val logbackversion = "1.2.3"

plugins {
    kotlin("jvm") version "1.5.10"
    application
    kotlin("plugin.serialization") version "1.5.0"
}

group = "me.admin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation ("ch.qos.logback:logback-classic:$logbackversion")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20")
    implementation("org.jetbrains.exposed:exposed-core:0.32.1") //можно переехать на 0,32,1 там поправили exec для MS SQL
    implementation("org.jetbrains.exposed:exposed-dao:0.32.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.32.1")
    implementation("com.microsoft.sqlserver:mssql-jdbc:6.4.0.jre7")
    implementation("org.jetbrains.exposed:exposed-jodatime:0.32.1")
    implementation ("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.1")
    //implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}