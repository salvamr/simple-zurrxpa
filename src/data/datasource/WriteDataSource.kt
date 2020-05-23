package data.datasource

import domain.model.Identifiable
import io.reactivex.rxjava3.core.Completable

interface WriteDataSource<Key, Value : Identifiable<Key>> {
    fun save(value: Value): Completable
}