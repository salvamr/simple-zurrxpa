package data.offsets.api

import data.datasource.ReadDataSource
import domain.model.Offset

class OffsetsApiDataSource(
        private val offsetsApi: OffsetsApi
) : ReadDataSource<String, Offset> {
    companion object {
        const val OFFSETS_BASE_URL = "https://raw.githubusercontent.com/"
    }

    override suspend fun getAll(): List<Offset> = offsetsApi.getOffsets().toDomain()
}