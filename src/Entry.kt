import data.api.RetrofitApiClient
import data.offsets.api.OffsetsApi
import data.offsets.api.OffsetsApiDataSource
import data.offsets.api.OffsetsApiDataSource.Companion.OFFSETS_BASE_URL
import data.offsets.cache.OffsetsCacheDataSource
import domain.process.GameProcess
import domain.repository.offsets.OffsetsRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.Main

fun main() {
    startKoin { modules(data, domain) }
    Main()
}

private val data = module {
    //retrofit
    single { RetrofitApiClient() }
    single<OffsetsApi> { (get() as RetrofitApiClient)(OFFSETS_BASE_URL).create(OffsetsApi::class.java) }

    // datasources
    single { OffsetsApiDataSource(get()) }
    single { OffsetsCacheDataSource() }

    //repositories
    single { OffsetsRepository(get(), get()) }
}

private val domain = module {
    single { GameProcess() }
}