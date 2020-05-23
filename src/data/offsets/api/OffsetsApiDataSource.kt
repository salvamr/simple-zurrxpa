package data.offsets.api

import data.datasource.ReadDataSource
import domain.model.Offset
import io.reactivex.rxjava3.core.Maybe

class OffsetsApiDataSource(
        private val offsetsApi: OffsetsApi
) : ReadDataSource<String, Offset> {
    companion object {
        const val OFFSETS_BASE_URL = "https://raw.githubusercontent.com/"
    }

    override fun getAll(): Maybe<List<Offset>> = offsetsApi.getOffsets().map { it.toDomain() }.toMaybe()

    override fun getByKey(key: String): Maybe<Offset> = Maybe.empty()
}