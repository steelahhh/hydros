package app.hydros.arch

import com.bumble.appyx.core.plugin.NodeLifecycleAware
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface Connectable<Input : Any, Output : Any> : NodeLifecycleAware {
  val input: Channel<Input>
  val output: Flow<Output>
}

class NodeConnector<Input : Any, Output : Any>(
  override val input: Channel<Input> = Channel(UNLIMITED),
) : Connectable<Input, Output> {
  private val outputChannel = Channel<Output>(UNLIMITED)

  override val output: Flow<Output> = outputChannel.receiveAsFlow()

  fun send(output: Output) {
    outputChannel.trySend(output)
  }
}
