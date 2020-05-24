package data.process.entity

import data.process.GameProcess
import domain.model.Offset
import domain.repository.offsets.OffsetsRepository
import io.reactivex.rxjava3.core.Observable

class LocalPlayer(
        private val offsetsRepository: OffsetsRepository,
        private val gameProcess: GameProcess
) {
    val localPlayer: Int by lazy {
        offsetsRepository.getByKey(Offset.LOCAL_PLAYER)
                .flatMapObservable { localPlayerOffset ->
                    with (gameProcess) {
                        val result = process.int(clientDllAddress + localPlayerOffset.value)
                        Observable.just(result)
                    }
                }.blockingFirst()
    }

    fun isJumping(): Observable<Boolean> = offsetsRepository.getByKey(Offset.FLAGS)
            .flatMapObservable { flagsOffset ->
                with (gameProcess) {
                    val result = process.int(localPlayer + flagsOffset.value)
                    Observable.just(result and (1 shl 0) != 1)
                }
            }
}