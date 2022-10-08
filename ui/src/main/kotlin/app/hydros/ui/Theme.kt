package app.hydros.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.kyant.monet.LocalTonalPalettes
import com.kyant.monet.PaletteStyle
import com.kyant.monet.TonalPalettes.Companion.toTonalPalettes
import com.kyant.monet.dynamicColorScheme

@Composable
fun HydrosTheme(content: @Composable () -> Unit) {
  val palettes = Color.Blue.toTonalPalettes(style = PaletteStyle.TonalSpot)

  CompositionLocalProvider(LocalTonalPalettes provides palettes) {
    MaterialTheme(colorScheme = dynamicColorScheme(), content = content)
  }
}
