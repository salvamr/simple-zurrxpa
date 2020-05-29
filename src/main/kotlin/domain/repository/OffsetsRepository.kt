package domain.repository

import domain.model.Offset

interface OffsetsRepository {
    suspend fun sync(): Boolean
    suspend fun getByKey(key: String): Offset
}