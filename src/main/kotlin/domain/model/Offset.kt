package domain.model

data class Offset(val name: String, val value: Int) : Identifiable<String> {
    override val id: String = name

    companion object {
        const val LOCAL_PLAYER = "dwLocalPlayer"
        const val ENTITY_PLAYER = "dwEntityList"
        const val FLAGS = "m_fFlags"
        const val HEALTH = "m_iHealth"
        const val CROSSHAIR_ID = "m_iCrosshairId"
        const val GUN_GAME_IMMUNITY = "m_bGunGameImmunity"
        const val DORMANT = "m_bDormant"
        const val TEAM_NUM = "m_iTeamNum"
    }
}