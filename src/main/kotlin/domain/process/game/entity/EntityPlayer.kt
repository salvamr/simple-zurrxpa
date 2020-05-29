package domain.process.game.entity

import com.sun.javafx.geom.Vec3d
import domain.model.Offset
import domain.process.game.GameProcess
import domain.repository.OffsetsRepository

class EntityPlayer(
    private val gameProcess: GameProcess,
    private val offsetsRepository: OffsetsRepository
) {

    private suspend fun getEntity(index: Int): Int =
        with(gameProcess) {
            val entityPlayerOffset = offsetsRepository.getByKey(Offset.ENTITY_PLAYER)
            process.int(clientDllAddress + entityPlayerOffset.value + (index - 1) * 0x10)
        }

    suspend fun getHealth(index: Int): Int =
        with(gameProcess) {
            val healthOffset = offsetsRepository.getByKey(Offset.HEALTH)
            process.int(getEntity(index) + healthOffset.value)
        }

    suspend fun isInvisible(index: Int): Boolean =
        with(gameProcess) {
            val gunGameImmunity = offsetsRepository.getByKey(Offset.GUN_GAME_IMMUNITY)
            process.boolean(getEntity(index) + gunGameImmunity.value)
        }

    suspend fun isDormant(index: Int): Boolean =
        with(gameProcess) {
            val dormantOffset = offsetsRepository.getByKey(Offset.DORMANT)
            process.boolean(getEntity(index) + dormantOffset.value)
        }

    /*suspend fun isAlive(index: Int): Boolean =
        with(gameProcess) {
            val lifeStateOffset = offsetsRepository.getByKey(Offset.LIFE_STATE)
            process.boolean(getEntity(index) + lifeStateOffset.value)
        }*/

    suspend fun getTeam(index: Int): Int =
        with(gameProcess) {
            val teamNumOffset = offsetsRepository.getByKey(Offset.TEAM_NUM)
            process.int(getEntity(index) + teamNumOffset.value)
        }

    suspend fun getBoneMatrix(index: Int): Int =
        with(gameProcess) {
            val boneMatrixOffset = offsetsRepository.getByKey(Offset.BONE_MATRIX)
            process.int(getEntity(index) + boneMatrixOffset.value)
        }

    suspend fun getBone(index: Int, boneId: Int): Vec3d =
        with(gameProcess) {
            return Vec3d(
                process.double(getBoneMatrix(index) + 0x30 * boneId + 0x0C),
                process.double(getBoneMatrix(index) + 0x30 * boneId + 0x0C),
                process.double(getBoneMatrix(index) + 0x30 * boneId + 0x0C)
            )
        }

    suspend fun getTotalPlayerList(localPlayerTeamId: Int): List<Int> =
        with(gameProcess) {
            val clientStateOffset = offsetsRepository.getByKey(Offset.CLIENT_STATE)
            val maxPlayerOffset = offsetsRepository.getByKey(Offset.MAX_PLAYER)
            val clientState = process.int(engineDllAddress + clientStateOffset.value)
            val totalPlayers = process.int(clientState + maxPlayerOffset.value)

            (0 until totalPlayers).filter { playerId ->
                isDormant(playerId).not() && getHealth(playerId) > 0 && isInvisible(playerId).not() && getTeam(playerId) != localPlayerTeamId
            }
        }

}