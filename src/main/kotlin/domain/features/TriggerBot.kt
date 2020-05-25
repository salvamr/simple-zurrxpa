package domain.features

import data.process.game.entity.EntityPlayer
import data.process.game.entity.LocalPlayer
import presentation.TRIGGER_BOT_KEY

class TriggerBot(
    private val localPlayer: LocalPlayer,
    private val entityPlayer: EntityPlayer
) : Feature() {
    override suspend fun run() {
        val crosshairId = localPlayer.getCrosshairId()
        if (userInput.isKeyPressed(TRIGGER_BOT_KEY) &&
            crosshairId != 0 &&
            isEnemyPlayerAlive(crosshairId) &&
            areSameTeam(crosshairId).not()
        ) {
            userInput.leftClick()
        }
    }

    private suspend fun isEnemyPlayerAlive(index: Int) = entityPlayer.getHealth(index) > 0

    private suspend fun areSameTeam(index: Int) = entityPlayer.getTeam(index) == localPlayer.getTeam()
}