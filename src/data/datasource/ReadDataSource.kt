package data.datasource

import domain.model.Identifiable
import io.reactivex.rxjava3.core.Maybe

interface ReadDataSource<Key, Value : Identifiable<Key>> {
    fun getAll(): Maybe<List<Value>>
    fun getByKey(key: Key): Maybe<Value>
}