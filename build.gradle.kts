plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.ktlint.gradle)
        classpath(libs.buildkonfig.gradle.plugin)
    }
}
