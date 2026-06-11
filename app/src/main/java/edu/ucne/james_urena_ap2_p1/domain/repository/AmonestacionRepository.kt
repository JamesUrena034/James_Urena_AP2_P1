package edu.ucne.james_urena_ap2_p1.domain.repository

import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAll(): Flow<List<Amonestacion>>
    suspend fun getById(id: Int): Amonestacion?
    suspend fun save(amonestacion: Amonestacion)
    suspend fun delete(amonestacion: Amonestacion)
}