package domain.model

data class Offset(val name: String, val value: Int) : Identifiable<String> {
    override val id: String = name

    companion object {
        const val LOCAL_PLAYER = "dwLocalPlayer"
        const val FLAGS = "m_fFlags"
    }
}