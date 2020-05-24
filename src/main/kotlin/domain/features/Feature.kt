package domain.features

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class Feature : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined

    abstract suspend fun run()

    operator fun invoke() = launch {
        //TODO: While game is opened
        while (true) {
            run()
        }
    }
}