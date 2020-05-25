package presentation

import data
import data.process.game.entity.EntityPlayer
import data.process.game.entity.LocalPlayer
import domain
import domain.features.BunnyHop
import domain.features.TriggerBot
import domain.repository.offsets.OffsetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

object Main : KoinComponent {
    private val bunnyHop: BunnyHop by inject()
    private val triggerBot: TriggerBot by inject()
    private val offsetsRepository: OffsetsRepository by inject()


    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        startKoin { modules(data, domain) }
        println("Starting: ${Thread.currentThread().name}")
        val synced = offsetsRepository.sync()
        println("Repository synced: $synced")

        bunnyHop()
        triggerBot()
    }
}