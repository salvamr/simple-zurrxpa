package domain.features

import com.sun.javafx.geom.Vec3d
import domain.model.VectorX
import domain.process.game.entity.EntityPlayer
import domain.process.game.entity.LocalPlayer

class Aimbot(
    private val localPlayer: LocalPlayer,
    private val entityPlayer: EntityPlayer
) : Feature() {

    override suspend fun run() {

    }

    private suspend fun getBestTarget() {
        val localPlayerTeamId = localPlayer.getTeam()
        val gamePlayerIdList = entityPlayer.getTotalPlayerList(localPlayerTeamId)
        if (gamePlayerIdList.isEmpty()) {
            return
        }


    }

    private fun GetFov(vectorAngles: VectorX, vectorSource: VectorX, vectorDestination: VectorX) {
        val aimAngle = vectorSource.calculateAngle(vectorDestination)

    }

    private fun makeVector(vector: VectorX):
}