package app.hydros.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

interface Presenter<Model : BaseUiModel> {
  @Composable fun present(): Model
}

@Suppress("NOTHING_TO_INLINE")
@Composable
inline fun <E : Any> CollectEffect(
  events: Flow<E>,
  noinline onEvent: suspend CoroutineScope.(E) -> Unit,
) {
  LaunchedEffect(events) { events.collectLatest { onEvent(it) } }
}
