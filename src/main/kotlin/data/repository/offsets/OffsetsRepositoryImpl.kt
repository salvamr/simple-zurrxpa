package data.repository.offsets

import data.api.offsets.OffsetsApiDataSource
import data.cache.offsets.OffsetsCacheDataSource
import domain.model.Offset
import data.repository.Repository
import domain.repository.OffsetsRepository

internal class OffsetsRepositoryImpl(
    offsetsApiDataSource: OffsetsApiDataSource,
    private val offsetsCacheDataSource: OffsetsCacheDataSource
) : Repository<String, Offset>(readDataSource = offsetsApiDataSource, cacheDataSource = offsetsCacheDataSource), OffsetsRepository {

    override suspend fun sync() = getAll().isNotEmpty()

    override suspend fun getByKey(key: String) = offsetsCacheDataSource.getByKey(key)
}