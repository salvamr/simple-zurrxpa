package data.offsets.api

import data.offsets.api.model.OffsetsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface OffsetsApi {
    @GET("frk1/hazedumper/master/csgo.json")
    fun getOffsets(): Single<OffsetsResponse>
}