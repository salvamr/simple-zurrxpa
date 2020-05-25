package data.process.game.entity

import data.cache.offsets.OffsetsCacheDataSource
import data.process.game.GameProcess
import domain.model.Offset

class LocalPlayer(
    private val gameProcess: GameProcess,
    private val offsetsCacheDataSource: OffsetsCacheDataSource
) {

    private suspend fun getPlayer(): Int =
        with(gameProcess) {
            val localPlayerOffset = offsetsCacheDataSource.getByKey(Offset.LOCAL_PLAYER)
            process.int(clientDllAddress + localPlayerOffset.value)
        }

    suspend fun isJumping(): Boolean =
        with(gameProcess) {
            val flagsOffset = offsetsCacheDataSource.getByKey(Offset.FLAGS)
            val result = process.int(getPlayer() + flagsOffset.value)
            result and (1 shl 0) != 1
        }

    suspend fun getCrosshairId(): Int =
        with(gameProcess) {
            val crosshairIdOffset = offsetsCacheDataSource.getByKey(Offset.CROSSHAIR_ID)
            process.int(getPlayer() + crosshairIdOffset.value)
        }

    suspend fun getTeam(): Int =
        with(gameProcess) {
            val teamNumOffset = offsetsCacheDataSource.getByKey(Offset.TEAM_NUM)
            process.int(getPlayer() + teamNumOffset.value)
        }
}