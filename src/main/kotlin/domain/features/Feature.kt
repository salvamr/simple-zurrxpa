package domain.features

import domain.process.keyboard.UserKeyboardManager
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

abstract class Feature : CoroutineScope, KoinComponent {
    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined + CoroutineName(javaClass.simpleName)

    protected val userInput: UserKeyboardManager by inject()

    abstract suspend fun run()

    operator fun invoke() = launch {
        println(Thread.currentThread().name)
        while (true) {
            delay(1)
            run()
        }
    }
}