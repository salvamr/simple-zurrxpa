package domain.features

import domain.process.game.entity.LocalPlayer
import presentation.BUNNY_HOP_KEY

class BunnyHop(
    private val localPlayer: LocalPlayer
) : Feature() {
    override suspend fun run() {
        if (userInput.isKeyPressed(BUNNY_HOP_KEY) and localPlayer.isJumping().not()) {
            userInput.mouseWheelDown()
        }
    }
}