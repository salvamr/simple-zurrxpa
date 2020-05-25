package presentation

import data
import data.process.game.GameProcess
import domain
import domain.features.BunnyHop
import domain.features.TriggerBot
import domain.repository.offsets.OffsetsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.cancel
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import kotlin.system.exitProcess

object Main : KoinComponent {
    private val bunnyHop: BunnyHop by inject()
    private val triggerBot: TriggerBot by inject()

    private val offsetsRepository: OffsetsRepository by inject()
    private val gameProcess: GameProcess by inject()

    private val features = mutableListOf<Job>()

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        startKoin { modules(data, domain) }

        println("Repository sync status: ${offsetsRepository.sync()}")

        with(features) {
            add(bunnyHop())
            add(triggerBot())
        }

        while(gameProcess.isProcessAlive()) { delay(1000) }

        println("Leaving ...")
        features.forEach { it.cancel() }
        cancel()
        exitProcess(0)
    }
}