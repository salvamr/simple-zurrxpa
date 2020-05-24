package presentation

import data.process.entity.LocalPlayer
import domain.repository.offsets.OffsetsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Thread.sleep
import javax.xml.bind.JAXBElement

class Main : KoinComponent {

    private val localPlayer: LocalPlayer by inject()
    private val offsetsRepository: OffsetsRepository by inject()

    init {
        runBlocking {
            val timeStamp = System.currentTimeMillis()
            val synced = offsetsRepository.sync()
            println("Repository: $synced time: ${System.currentTimeMillis() - timeStamp}")
            val timeStamp2 = System.currentTimeMillis()
            val synced2 = offsetsRepository.sync()
            println("Repository: $synced2 time: ${System.currentTimeMillis() - timeStamp2}")
        }
    }
}