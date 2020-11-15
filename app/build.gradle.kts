import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs
import com.yasintanriverdi.moviescompose.buildsrc.utils.getLocalProperty

plugins {
    id(Plugins.androidApplication)
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
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)

    implementation(Dependencies.Google.material)
}
