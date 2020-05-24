package data.datasource

import domain.model.Identifiable

interface CacheDataSource<Key, Value : Identifiable<Key>> : ReadDataSource<Key, Value>, WriteDataSource<Key, Value> {
    suspend fun getByKey(key: Key): Value?
    suspend fun clear()
}