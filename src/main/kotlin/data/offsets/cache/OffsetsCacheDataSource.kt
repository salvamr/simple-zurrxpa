package data.offsets.cache

import data.datasource.CacheDataSource
import domain.model.Offset

class OffsetsCacheDataSource : CacheDataSource<String, Offset> {
    private val cache = linkedMapOf<String, Offset>()

    override suspend fun getAll(): List<Offset> = cache.map { it.value }

    override suspend fun getByKey(key: String): Offset = cache[key]!!

    override suspend fun save(value: Offset) { cache[value.id] = value }

    override suspend fun clear() = cache.clear()

}