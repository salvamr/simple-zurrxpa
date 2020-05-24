package data.offsets.cache

import data.datasource.CacheDataSource
import domain.model.Offset
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class OffsetsCacheDataSource : CacheDataSource<String, Offset> {
    private val cache = hashMapOf<String, Offset>()

    override fun getAll(): Maybe<List<Offset>> =
            if (cache.isNotEmpty()) {
                Maybe.just(cache.map { it.value })
            } else {
                Maybe.empty()
            }

    override fun getByKey(key: String): Maybe<Offset> =
            Maybe.create { emitter ->
                cache[key]?.let {
                    emitter.onSuccess(it)
                } ?: emitter.onComplete()
            }

    override fun save(value: Offset): Completable =
            Completable.create { emitter ->
                cache[value.id] = value
                emitter.onComplete()
            }

    override fun clear(): Completable = Completable.fromAction { cache.clear() }

}