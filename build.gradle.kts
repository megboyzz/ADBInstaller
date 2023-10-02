import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "ru.megboyzz.adb"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(mapOf("path" to ":domain", "configuration" to "default")))
                implementation(project(mapOf("path" to ":data", "configuration" to "default")))

                implementation("com.google.code.findbugs:jsr305:3.0.2")
                implementation("cafe.adriel.voyager:voyager-transitions-desktop:1.0.0-rc06")
                implementation("cafe.adriel.voyager:voyager-navigator-desktop:1.0.0-rc06")
                implementation("cafe.adriel.voyager:voyager-core-desktop:1.0.0-rc06")

            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ADBInstaller"
            packageVersion = "1.0.1"

            appResourcesRootDir.set(
                project
                    .layout
                    .projectDirectory
                    .dir("src/jvmMain/resources")
            )
        }
    }
}
