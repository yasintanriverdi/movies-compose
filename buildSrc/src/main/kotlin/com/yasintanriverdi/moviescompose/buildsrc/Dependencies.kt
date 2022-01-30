object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"

        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
            const val paging = "androidx.paging:paging-compose:${Versions.composePaging}"
        }

        object Lifecycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"

        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    }

    object Hilt {
        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

}
