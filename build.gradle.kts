plugins {
    kotlin("multiplatform") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("com.android.application")
}

group = "id.walt"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")

    maven("https://maven.walt.id/repository/waltid/")
    maven("https://maven.walt.id/repository/waltid-ssi-kit/")
    maven("https://repo.danubetech.com/repository/maven-public/")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("co.touchlab:kermit:1.1.3")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                implementation("io.ktor:ktor-server-core:2.0.3")
                implementation("io.ktor:ktor-server-cio:2.0.3")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.slf4j:slf4j-simple:2.0.0-alpha7")

                implementation("id.walt.servicematrix:WaltID-ServiceMatrix:1.1.1")
                implementation("id.walt:waltid-ssi-kit:1.11.0-SNAPSHOT")
                implementation("id.walt:waltid-ssikit-vclib:1.22.0")

                implementation("com.nimbusds:oauth2-oidc-sdk:9.37.2")

                // to remove:
                implementation("io.javalin:javalin:4.6.4")
                implementation("com.github.kmehrunes:javalin-jwt:0.3")
                implementation("com.auth0:java-jwt:4.0.0")
                implementation("com.beust:klaxon:5.6")
                implementation("com.google.guava:guava:31.1-jre")

            }
        }
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
        val androidMain by getting {
            dependencies {
                rootProject
                implementation("com.google.android.material:material:1.6.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
    }
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        applicationId = "id.walt.library"
        minSdk = 24
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
