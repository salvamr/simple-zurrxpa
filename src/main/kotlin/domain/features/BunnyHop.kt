package domain.features

import data.process.entity.LocalPlayer

class BunnyHop(
    private val localPlayer: LocalPlayer
) : Feature() {

    override suspend fun run() {
        if (localPlayer.isJumping().not()) {
            localPlayer.jump()
        }
    }
}