package app.hydros.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.node.NodeView

@Immutable interface BaseUiModel

interface Ui<UiState : BaseUiModel> {
  @Composable fun Content(modifier: Modifier, state: UiState)
}

inline fun <UiState : BaseUiModel> ui(
  crossinline body:
    @Composable
    (
      modifier: Modifier,
      state: UiState,
    ) -> Unit,
): Ui<UiState> =
  object : Ui<UiState> {
    @Composable
    override fun Content(modifier: Modifier, state: UiState) {
      body(modifier, state)
    }
  }

fun <M : BaseUiModel> nodeView(
  presenter: Presenter<M>,
  ui: Ui<M>,
): NodeView =
  object : NodeView {
    @Composable
    override fun View(modifier: Modifier) {
      val state = presenter.present()

      ui.Content(modifier = modifier, state = state)
    }
  }
