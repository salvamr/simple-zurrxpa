package data.api.offsets.model

import com.google.gson.annotations.SerializedName
import data.model.DataEntity
import domain.model.Offset

data class OffsetsResponse(
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("netvars")
    val netvarsResponse: NetvarsResponse,
    @SerializedName("signatures")
    val signaturesResponse: SignaturesResponse
) : DataEntity<String, List<Offset>> {
    override val id: String = timestamp.toString()

    override fun toDomain(): List<Offset> =
        netvarsResponse.getNetvars().toMutableList().apply {
            addAll(signaturesResponse.getSignatures())
        }.map { Offset(it.first, it.second) }
}