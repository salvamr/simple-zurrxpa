package domain.process.game.entity

import domain.process.game.GameProcess
import domain.model.Offset
import domain.model.VectorX
import domain.repository.OffsetsRepository

class LocalPlayer(
    private val gameProcess: GameProcess,
    private val offsetsRepository: OffsetsRepository
) {

    private suspend fun getPlayer(): Int =
        with(gameProcess) {
            val localPlayerOffset = offsetsRepository.getByKey(Offset.LOCAL_PLAYER)
            process.int(clientDllAddress + localPlayerOffset.value)
        }

    suspend fun isJumping(): Boolean =
        with(gameProcess) {
            val flagsOffset = offsetsRepository.getByKey(Offset.FLAGS)
            val result = process.int(getPlayer() + flagsOffset.value)
            result and (1 shl 0) != 1
        }

    suspend fun getCrosshairId(): Int =
        with(gameProcess) {
            val crosshairIdOffset = offsetsRepository.getByKey(Offset.CROSSHAIR_ID)
            process.int(getPlayer() + crosshairIdOffset.value)
        }

    suspend fun getTeam(): Int =
        with(gameProcess) {
            val teamNumOffset = offsetsRepository.getByKey(Offset.TEAM_NUM)
            process.int(getPlayer() + teamNumOffset.value)
        }

    suspend fun getViewAngles(): VectorX =
        with()
}