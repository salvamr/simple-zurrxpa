package domain.model

data class Offset(val name: String, val value: Int) : Identifiable<String> {
    override val id: String = name
}