package com.yasintanriverdi.moviescompose.buildsrc

import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs.Versions.versionMajor
import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs.Versions.versionMinor
import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs.Versions.versionPatch

object AppConfigs {
    const val applicationId = "com.yasintanriverdi.moviescompose"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Versions {
        const val compileSdk = 30
        const val minSdk = 24
        const val targetSdk = 30

        internal const val versionMajor = 0
        internal const val versionMinor = 0
        internal const val versionPatch = 1
    }

    val versionCode
        get() = versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100

    val versionName
        get() = "${versionMajor}.${versionMinor}.${versionPatch}"
}