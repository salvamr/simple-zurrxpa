import data.api.RetrofitApiClient
import data.api.offsets.OffsetsApi
import data.api.offsets.OffsetsApiDataSource
import data.cache.offsets.OffsetsCacheDataSource
import data.process.game.GameProcess
import data.process.game.entity.EntityPlayer
import data.process.game.entity.LocalPlayer
import data.process.keyboard.UserKeyboardManager
import domain.features.BunnyHop
import domain.features.TriggerBot
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
    single { UserKeyboardManager() }

    single { LocalPlayer(get(), get()) }
    single { EntityPlayer(get(), get()) }

    //features
    single { BunnyHop(get()) }
    single { TriggerBot(get(), get()) }
}