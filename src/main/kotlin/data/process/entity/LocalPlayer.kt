package data.process.entity

import data.cache.offsets.OffsetsCacheDataSource
import data.process.GameProcess
import domain.model.Offset
import kotlinx.coroutines.runBlocking

class LocalPlayer(
    private val gameProcess: GameProcess,
    private val offsetsCacheDataSource: OffsetsCacheDataSource
) {
    val localPlayer: Int by lazy {
        runBlocking {
            with(gameProcess) {
                val localPlayerOffset = offsetsCacheDataSource.getByKey(Offset.LOCAL_PLAYER)
                process.int(clientDllAddress + localPlayerOffset.value)
            }
        }
    }

    suspend fun isJumping(): Boolean =
        with(gameProcess) {
            val flagsOffset = offsetsCacheDataSource.getByKey(Offset.FLAGS)
            val result = process.int(localPlayer + flagsOffset.value)
            result and (1 shl 0) != 1
        }

    suspend fun jump() = with(gameProcess) {
        val forceJumpOffset = offsetsCacheDataSource.getByKey(Offset.FORCE_JUMP)
        process[clientDllAddress + forceJumpOffset.value] = 6
    }
}