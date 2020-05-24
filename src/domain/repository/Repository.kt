package domain.repository

import data.datasource.CacheDataSource
import data.datasource.ReadDataSource
import domain.model.Identifiable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class Repository<Key, Value : Identifiable<Key>>(
        private val readDataSource: ReadDataSource<Key, Value>? = null,
        private val cacheDataSource: CacheDataSource<Key, Value>
) : ReadDataSource<Key, Value> {
    override fun getAll(): Maybe<List<Value>> =
            Maybe.concat(
                    cacheDataSource.getAll(),
                    readDataSource?.getAll()
                            ?.doOnSuccess {
                                Observable.fromIterable(it)
                                        .doOnNext { value -> cacheDataSource.save(value).subscribe() }
                                        .subscribeOn(Schedulers.computation())
                                        .subscribe()
                            }
            ).firstElement()

    override fun getByKey(key: Key) = cacheDataSource.getByKey(key)
}