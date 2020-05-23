package data.datasource

import domain.model.Identifiable
import io.reactivex.rxjava3.core.Completable


interface CacheDataSource<Key, Value : Identifiable<Key>> : ReadDataSource<Key, Value>, WriteDataSource<Key, Value> {
    fun clear(): Completable
}