package data.offsets.api

import data.offsets.api.model.OffsetsResponse
import retrofit2.http.GET

interface OffsetsApi {
    @GET("frk1/hazedumper/master/csgo.json")
    suspend fun getOffsets(): OffsetsResponse
}