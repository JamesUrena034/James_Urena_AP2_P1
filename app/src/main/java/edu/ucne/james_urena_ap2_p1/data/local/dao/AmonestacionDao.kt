package edu.ucne.james_urena_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.james_urena_ap2_p1.data.local.entities.AmonestacionEntity
import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {

    @Query("SELECT * FROM Amonestaciones ORDER BY amonestacionId DESC")
    fun getAll(): Flow<List<AmonestacionEntity>>

    @Query("SELECT * FROM Amonestaciones WHERE amonestacionId = :id")
    suspend fun getById(id: Int): AmonestacionEntity?

    @Upsert
    suspend fun save(amonestacion: AmonestacionEntity)

    @Delete
    suspend fun delete(amonestacion: AmonestacionEntity)
}