@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.compatibility.get().toInt())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.compatibility.get().toInt())
}

dependencies {
    // Dagger Hilt
    implementation("com.google.dagger:hilt-core:2.48")

    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
}