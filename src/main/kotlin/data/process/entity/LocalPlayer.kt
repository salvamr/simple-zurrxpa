package data.process.entity

import data.offsets.cache.OffsetsCacheDataSource
import data.process.GameProcess
import domain.model.Offset
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class LocalPlayer(
        private val gameProcess: GameProcess,
        private val offsetsCacheDataSource: OffsetsCacheDataSource
) {
    /*val localPlayer: Int by lazy {
        offsetsCacheDataSource.getByKey(Offset.LOCAL_PLAYER)
                .doOnSuccess { localPlayerOffset ->
                    with(gameProcess) {
                        val result = process.int(clientDllAddress + localPlayerOffset.value)
                        Maybe.just(result)
                    }
                }.blockingGet()
    }

    suspend fun isJumping(): Boolean = coroutineScope {
        val flags = offsetsCacheDataSource.getByKey(Offset.FLAGS)!!
        with(gameProcess) {
            val result = process.int(localPlayer + flags.value)
            result and (1 shl 0) != 1
        }
    }*/
}