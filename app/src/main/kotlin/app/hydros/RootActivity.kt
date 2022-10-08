package app.hydros

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import app.hydros.ui.HydrosTheme
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class RootActivity : ComponentActivity() {
  private lateinit var integrationPoint: ActivityIntegrationPoint

  private fun createIntegrationPoint(savedInstanceState: Bundle?): ActivityIntegrationPoint =
    ActivityIntegrationPoint.createIntegrationPoint(
      activity = this,
      savedInstanceState = savedInstanceState
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    integrationPoint = createIntegrationPoint(savedInstanceState)

    setContent {
      HydrosTheme {
        val controller = rememberSystemUiController()
        val isDark = isSystemInDarkTheme()
        SideEffect {
          controller.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDark,
            transformColorForLightContent = { it },
            isNavigationBarContrastEnforced = false,
          )
        }

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          NodeHost(
            modifier = Modifier.fillMaxSize(),
            integrationPoint = integrationPoint,
            factory = ::RootNode,
          )
        }
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    integrationPoint.onActivityResult(requestCode, resultCode, data)
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    integrationPoint.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    integrationPoint.onSaveInstanceState(outState)
  }
}
