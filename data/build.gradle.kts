plugins {
    kotlin("kapt")
    kotlin("jvm")
    application
}

group = "ru.megboyzz.adb.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":domain", "configuration" to "default")))

    implementation("net.dongliu:apk-parser:2.4.2")
    kapt("com.google.dagger:dagger-compiler:2.47")
    implementation("com.google.dagger:dagger:2.47")
    implementation("com.google.guava:guava-base:r03")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}