package presentation

import domain.repository.offsets.OffsetsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class Main : KoinComponent {

    private val repo: OffsetsRepository by inject()

    init {
        println(repo.getAll().blockingGet().toString())
    }
}