import data.api.RetrofitApiClient
import data.offsets.api.OffsetsApi
import data.offsets.api.OffsetsApiDataSource
import data.offsets.cache.OffsetsCacheDataSource
import data.process.GameProcess
import data.process.entity.LocalPlayer
import domain.features.BunnyHop
import domain.repository.offsets.OffsetsRepository
import org.koin.dsl.module

val data = module {
    //retrofit
    single { RetrofitApiClient() }
    single<OffsetsApi> { (get() as RetrofitApiClient)(OffsetsApiDataSource.OFFSETS_BASE_URL).create(OffsetsApi::class.java) }

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
    single { BunnyHop(get())}
}