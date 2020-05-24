package presentation

import data.process.entity.LocalPlayer
import domain.repository.offsets.OffsetsRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Thread.sleep

class Main : KoinComponent {

    private val localPlayer: LocalPlayer by inject()
    private val offsetsRepository: OffsetsRepository by inject()

    init {
        offsetsRepository.sync()
                .doOnComplete {
                    println("Offsets synced")
                    localPlayer.isJumping()
                            .repeatUntil { false }
                            .subscribeOn(Schedulers.computation())
                            .subscribe {
                                println("Player status: $it")
                            }
                }
                .subscribe()

        while (true) {
            sleep(1)
        }
    }
}