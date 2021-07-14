import com.android.build.gradle.BaseExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.yasintanriverdi.moviescompose.buildsrc.AppConfigs

plugins {
    id(Plugins.spotless) version Versions.spotless
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Classpaths.androidGradlePlugin)
        classpath(Classpaths.kotlinGradlePlugin)
        classpath(Classpaths.daggerHiltPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {

    project.pluginManager.apply(Plugins.spotless)

    spotless {
        java {
            target("**/*.java")
            trimTrailingWhitespace()
            removeUnusedImports()
            googleJavaFormat()
        }

        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("0.39.0").userData(hashMapOf("indent_size" to "4", "android" to "true", "max_line_length" to "200"))
        }

        kotlinGradle {
            target("**/*.gradle.kts")
            ktlint("0.39.0").userData(hashMapOf("indent_size" to "4", "android" to "true", "max_line_length" to "200"))
        }

        format("misc") {
            target("**/.gitignore", "**/*.gradle", "**/*.md", "**/*.sh", "**/*.yml")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    project.tasks.withType(KotlinCompile::class) {
        kotlinOptions {
            freeCompilerArgs = listOf(
                // Opt-in to experimental compose APIs
                "-Xopt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including collectAsState()
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xskip-prerelease-check"
            )
            jvmTarget = "11"
        }
    }

    project.plugins.whenPluginAdded {
        when (this) {
            is AppPlugin, is LibraryPlugin -> {
                the<BaseExtension>().apply {
                    compileSdkVersion(AppConfigs.Versions.compileSdk)

                    defaultConfig {
                        minSdk = AppConfigs.Versions.minSdk
                        targetSdk = AppConfigs.Versions.minSdk
                    }

                    buildFeatures.compose = true
                    buildFeatures.viewBinding = true

                    composeOptions {
                        kotlinCompilerExtensionVersion = Versions.compose
                    }

                    compileOptions {
                        targetCompatibility = JavaVersion.VERSION_11
                        sourceCompatibility = JavaVersion.VERSION_11
                    }

                    sourceSets{
                        getByName("main") {
                            java.srcDir("src/main/kotlin")
                        }
                        getByName("test") {
                            java.srcDir("src/test/kotlin")
                        }
                        getByName("androidTest") {
                            java.srcDir("src/androidTest/kotlin")
                        }
                    }

                    lintOptions.lintConfig = rootProject.file("lint.xml")
                }
            }

            is JavaPlugin -> {
                the<JavaPluginExtension>().apply {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }
        }
    }
}
