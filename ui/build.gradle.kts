plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android { namespace = "app.hydros.ui" }

dependencies {
  api(libs.androidx.compose.animation)
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.material.material3)
  api(libs.androidx.compose.material.icons)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.ui.ui)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.compose.ui.tooling.data)
  api(libs.androidx.compose.ui.tooling.preview)

  implementation(libs.androidx.compose.monet)
}
