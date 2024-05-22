plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("androidx.room") version "2.6.1" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

    kotlin("jvm") version "1.9.0"

    kotlin("kapt") version "1.9.0"
}