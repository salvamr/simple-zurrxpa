package domain.features

import data.process.entity.LocalPlayer

class BunnyHop(
    private val localPlayer: LocalPlayer
) : Feature() {
    
    override suspend fun run() {
        println(Thread.currentThread().name)
        if (localPlayer.isJumping().not()) {
            localPlayer.jump()
        }
    }
}