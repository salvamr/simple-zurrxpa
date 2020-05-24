package data.datasource

import domain.model.Identifiable

interface WriteDataSource<Key, Value : Identifiable<Key>> {
    suspend fun save(value: Value)
}