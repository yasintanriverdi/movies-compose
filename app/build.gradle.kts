import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs
import com.yasintanriverdi.moviescompose.buildsrc.utils.getLocalProperty

plugins {
    id(Plugins.androidApplication)
    id(Plugins.daggerHilt)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKapt)
}

android {
    compileSdkVersion(AppConfigs.Versions.compileSdk)

    defaultConfig {
        applicationId = AppConfigs.applicationId
        minSdkVersion(AppConfigs.Versions.minSdk)
        targetSdkVersion(AppConfigs.Versions.targetSdk)
        versionCode = AppConfigs.versionCode
        versionName = AppConfigs.versionName

        testInstrumentationRunner = AppConfigs.testInstrumentationRunner
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }

        forEach {
            it.buildConfigField(
                type = "String",
                name = "TMDB_API_KEY", value = "\"${getLocalProperty("TMDB_API_KEY", project)}\""
            )
            it.buildConfigField(
                type = "String",
                name = "TMDB_API_BASE_URL", value = "\"https://api.themoviedb.org/3/\""
            )
            it.buildConfigField(
                type = "String",
                name = "SMALL_IMAGE_URL", value = "\"https://image.tmdb.org/t/p/w200\""
            )
            it.buildConfigField(
                type = "String",
                name = "LARGE_IMAGE_URL", value = "\"https://image.tmdb.org/t/p/w500\""
            )
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.activityKtx)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.Lifecycle.extensions)
    implementation(Dependencies.AndroidX.Lifecycle.runtime)
    implementation(Dependencies.AndroidX.Lifecycle.livedata)
    implementation(Dependencies.AndroidX.Lifecycle.viewmodel)

    // Compose
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.foundation)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.livedata)
    implementation(Dependencies.AndroidX.Compose.navigation)
    implementation(Dependencies.AndroidX.Compose.paging)

    // Google
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Hilt.core)
    implementation(Dependencies.Hilt.viewmodel)
    kapt(Dependencies.Hilt.compiler)
    kapt(Dependencies.Hilt.androidCompiler)

    // Retrofit-OkHttp
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.moshi)
    implementation(Dependencies.OkHttp.okhttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)

    implementation(Dependencies.coil)

}
