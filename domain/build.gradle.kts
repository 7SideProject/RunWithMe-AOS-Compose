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
    implementation(libs.hilt.core)

    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation(libs.converter.gson)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
}