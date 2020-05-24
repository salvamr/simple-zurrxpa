package domain.repository.offsets

import data.offsets.api.OffsetsApiDataSource
import data.offsets.cache.OffsetsCacheDataSource
import domain.model.Offset
import domain.repository.Repository
import io.reactivex.rxjava3.core.Completable

class OffsetsRepository(
        offsetsApiDataSource: OffsetsApiDataSource,
        offsetsCacheDataSource: OffsetsCacheDataSource
) : Repository<String, Offset>(readDataSource = offsetsApiDataSource, cacheDataSource = offsetsCacheDataSource) {

    fun sync(): Completable = Completable.create { emitter ->
        getAll().subscribe { emitter.onComplete() }
    }
}