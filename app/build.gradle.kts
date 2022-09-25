import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "app.hydros"

  defaultConfig {
    applicationId = "app.hydros"
    minSdk = 26
    targetSdk = 33
    versionCode = 100
    versionName = "0.0.1"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  testOptions { unitTests.isIncludeAndroidResources = true }
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    @Suppress("SuspiciousCollectionReassignment")
    freeCompilerArgs +=
      listOf(
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
      )
  }
}

dependencies {
  implementation(libs.androidx.compose.runtime)
}
