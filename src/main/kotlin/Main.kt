import data.process.entity.LocalPlayer
import domain.features.BunnyHop
import domain.repository.offsets.OffsetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

object Main : KoinComponent {
    private val bunnyHop: BunnyHop by inject()
    private val offsetsRepository: OffsetsRepository by inject()

    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        startKoin { modules(data, domain) }
        println("Starting: ${Thread.currentThread().name}")
        val synced = offsetsRepository.sync()
        println("Repository synced: $synced")
        println("Starting BunnyHop ... ${Thread.currentThread().name}")
        bunnyHop()
    }
}