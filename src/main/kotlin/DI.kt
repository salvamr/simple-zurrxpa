import data.api.RetrofitApiClient
import data.api.offsets.OffsetsApi
import data.api.offsets.OffsetsApiDataSource
import data.cache.offsets.OffsetsCacheDataSource
import domain.process.game.GameProcess
import domain.process.game.entity.EntityPlayer
import domain.process.game.entity.LocalPlayer
import domain.process.keyboard.UserKeyboardManager
import domain.features.BunnyHop
import domain.features.TriggerBot
import data.repository.offsets.OffsetsRepositoryImpl
import domain.features.Aimbot
import domain.repository.OffsetsRepository
import org.koin.dsl.module
import java.awt.Robot

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
    single<OffsetsRepository> { OffsetsRepositoryImpl(get(), get()) }
}

val domain = module {
    single { GameProcess() }
    single { UserKeyboardManager(Robot()) }

    single { LocalPlayer(get(), get()) }
    single { EntityPlayer(get(), get()) }

    //features
    single { BunnyHop(get()) }
    single { TriggerBot(get(), get()) }
    single { Aimbot(get(), get()) }
}