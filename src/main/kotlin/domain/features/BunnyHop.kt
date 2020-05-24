package domain.features

import data.process.game.entity.LocalPlayer
import data.process.keyboard.UserKeyboardManager

class BunnyHop(
    private val localPlayer: LocalPlayer,
    private val userKeyboardManager: UserKeyboardManager
) : Feature() {

    override suspend fun run() {
        if (userKeyboardManager.isKeyPressed(32) and localPlayer.isJumping().not()) {
            // or localPlayer.jump()
            userKeyboardManager.mouseWheelDown()
        }
    }
}