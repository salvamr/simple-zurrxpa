package domain.repository

import data.datasource.CacheDataSource
import data.datasource.ReadDataSource
import domain.model.Identifiable

abstract class Repository<Key, Value : Identifiable<Key>>(
    private val readDataSource: ReadDataSource<Key, Value>? = null,
    private val cacheDataSource: CacheDataSource<Key, Value>
) : ReadDataSource<Key, Value> {
    override suspend fun getAll(): List<Value> =
        cacheDataSource.getAll()
            .ifEmpty {
                readDataSource?.getAll()?.apply {
                    forEach {
                        cacheDataSource.save(it)
                    }
                } ?: emptyList()
            }
}