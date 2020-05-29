package presentation

import data
import domain.process.game.GameProcess
import domain
import domain.features.BunnyHop
import domain.features.TriggerBot
import domain.model.VectorX
import domain.repository.OffsetsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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

        val v1 = VectorX(2.0,2.0,2.0)
        val v2 = VectorX(3.0,3.0,1.0)

        println("Angle is: ${v1.calculateAngle(v2)}")

        with(features) {
            add(bunnyHop())
            add(triggerBot())
        }

        while (gameProcess.isProcessAlive()) {
            delay(1000)
        }

        println("Leaving ...")
        features.forEach { it.cancel() }
        cancel()
        exitProcess(0)
    }
}