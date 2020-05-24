import data.api.RetrofitApiClient
import data.api.offsets.OffsetsApi
import data.api.offsets.OffsetsApiDataSource
import data.cache.offsets.OffsetsCacheDataSource
import data.process.GameProcess
import data.process.entity.LocalPlayer
import domain.features.BunnyHop
import domain.repository.offsets.OffsetsRepository
import org.koin.dsl.module

val data = module {
    //retrofit
    single { RetrofitApiClient() }
    single<OffsetsApi> {
        (get() as RetrofitApiClient)(OffsetsApiDataSource.OFFSETS_BASE_URL).create(
            OffsetsApi::class.java
        )
    }

    // datasources
    single { OffsetsApiDataSource(get()) }
    single { OffsetsCacheDataSource() }

    //repositories
    single { OffsetsRepository(get(), get()) }
}

val domain = module {
    single { GameProcess() }

    single { LocalPlayer(get(), get()) }

    //features
    single { BunnyHop(get()) }
}