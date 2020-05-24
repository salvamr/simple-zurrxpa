package domain.repository.offsets

import data.api.offsets.OffsetsApiDataSource
import data.cache.offsets.OffsetsCacheDataSource
import domain.model.Offset
import domain.repository.Repository

class OffsetsRepository(
    offsetsApiDataSource: OffsetsApiDataSource,
    offsetsCacheDataSource: OffsetsCacheDataSource
) : Repository<String, Offset>(readDataSource = offsetsApiDataSource, cacheDataSource = offsetsCacheDataSource) {

    suspend fun sync() = getAll().isNotEmpty()
}