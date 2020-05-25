package data.process.game.entity

import data.cache.offsets.OffsetsCacheDataSource
import data.process.game.GameProcess
import domain.model.Offset

class EntityPlayer(
    private val gameProcess: GameProcess,
    private val offsetsCacheDataSource: OffsetsCacheDataSource
) {

    private suspend fun getEntity(index: Int): Int =
        with(gameProcess) {
            val entityPlayerOffset = offsetsCacheDataSource.getByKey(Offset.ENTITY_PLAYER)
            process.int(clientDllAddress + entityPlayerOffset.value + (index - 1) * 0x10)
        }

    suspend fun getHealth(index: Int): Int =
        with(gameProcess) {
            val healthOffset = offsetsCacheDataSource.getByKey(Offset.HEALTH)
            process.int(getEntity(index) + healthOffset.value)
        }

    suspend fun isInvisible(index: Int): Boolean =
        with(gameProcess) {
            val gunGameImmunity = offsetsCacheDataSource.getByKey(Offset.GUN_GAME_IMMUNITY)
            process.boolean(getEntity(index) + gunGameImmunity.value)
        }

    suspend fun isDormant(index: Int): Boolean =
        with(gameProcess) {
            val dormantOffset = offsetsCacheDataSource.getByKey(Offset.DORMANT)
            process.boolean(getEntity(index) + dormantOffset.value)
        }

    suspend fun getTeam(index: Int): Int =
        with(gameProcess) {
            val teamNumOffset = offsetsCacheDataSource.getByKey(Offset.TEAM_NUM)
            process.int(getEntity(index) + teamNumOffset.value)
        }

}