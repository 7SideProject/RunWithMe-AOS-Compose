import java.util.Properties
import java.io.FileInputStream

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    id("kotlin-kapt")
}

val properties = Properties().apply {
    load(FileInputStream(project.rootProject.file("local.properties")))
}

android {
    namespace = "com.side.runwithme"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.side.runwithme"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 2
        versionName = "1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASEURL", "${properties.getProperty("BASEURL")}")
        buildConfigField("String", "NAVERAPIKEY", "${properties.getProperty("NAVERAPIKEY")}")
        buildConfigField("String", "MAIL_ID", "${properties.getProperty("MAIL_ID")}")
        buildConfigField("String", "MAIL_PASSWORD", "${properties.getProperty("MAIL_PASSWORD")}")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.compatibility.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.compatibility.get().toInt())
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.version.get()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":designsystem"))

    implementation(libs.core.ktx)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.activity.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    debugImplementation(libs.compose.ui.tooling)

    // lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // navigation
    implementation(libs.navigation.compose)

    // navigation animation
    implementation(libs.accompanist.navigation.animation)

    // retrofit
    implementation(libs.retrofit)

    // okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Dagger Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // lottie
    implementation(libs.lottie.compose)

    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation(libs.converter.gson)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
}