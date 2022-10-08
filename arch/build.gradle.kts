plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android { namespace = "app.hydros.arch" }

dependencies {
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui.ui)
  api(libs.androidx.compose.ui.util)
  api(libs.appyx.core)
}
