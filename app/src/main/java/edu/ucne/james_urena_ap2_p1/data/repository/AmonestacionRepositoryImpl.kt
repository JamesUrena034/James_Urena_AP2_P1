package edu.ucne.james_urena_ap2_p1.data.repository

import edu.ucne.james_urena_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.james_urena_ap2_p1.data.mapper.toDomain
import edu.ucne.james_urena_ap2_p1.data.mapper.toEntity
import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import edu.ucne.james_urena_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val dao: AmonestacionDao
) : AmonestacionRepository {

    override fun observeAll(): Flow<List<Amonestacion>> =
        dao.getAll().map { it.map { entity -> entity.toDomain() } }

    override suspend fun getById(id: Int): Amonestacion? =
        dao.getById(id)?.toDomain()

    override suspend fun save(amonestacion: Amonestacion) =
        dao.save(amonestacion.toEntity())

    override suspend fun delete(amonestacion: Amonestacion) =
        dao.delete(amonestacion.toEntity())
}