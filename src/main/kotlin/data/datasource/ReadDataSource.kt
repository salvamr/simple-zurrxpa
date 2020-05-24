package data.datasource

import domain.model.Identifiable

interface ReadDataSource<Key, Value : Identifiable<Key>> {
    suspend fun getAll(): List<Value>
}