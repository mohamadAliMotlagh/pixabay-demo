import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.gradle.api.internal.properties.GradleProperties
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("com.codingfeline.buildkonfig")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
}

ktlint {
    android.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.auth)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.serialization)

            implementation(libs.koin.common)

            implementation(libs.kotlin.coroutine)

            implementation(libs.sqlDelight.runtime)
            implementation(libs.sqlDelight.corutine)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.koin.android)
            implementation(libs.kotlin.coroutine.android)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)

            implementation(libs.sqlDelight.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.ios)
            implementation(libs.sqlDelight.native)
        }
    }
}

buildkonfig {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("apikey.properties")

    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    } else {
        throw GradleException("Error: apikey.properties file not found. Please create it in the project root directory and define the API_KEY property.")
    }

    try {
        properties.load(localPropertiesFile.inputStream())
    } catch (e: Exception) {
        throw GradleException(
            "Error: Failed to load apikey.properties file. Please ensure the file is valid.",
            e
        )
    }

    val apiKey = properties.getProperty("API_KEY") ?: ""
    if (apiKey.isEmpty()){
        throw GradleException("Error: API_KEY witch located in apikey.properties is Empty. please provide an api key from this site: https://pixabay.com/service/about/api/")
    }
    packageName = "com.app.pixabay"
    objectName = "SharedConfig"
    //exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
        buildConfigField(STRING, "api_key", apiKey)
    }
}

android {
    namespace = "com.app.pixabay"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.animation.android)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutine.test)
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}

sqldelight {
    database("PixPayBackDatabase") {
        packageName = "com.app.pixabay.database"
    }
}
