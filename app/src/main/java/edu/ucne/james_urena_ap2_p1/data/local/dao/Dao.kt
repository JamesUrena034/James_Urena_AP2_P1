package edu.ucne.james_urena_ap2_p1.data.local.dao

import androidx.room.Dao
import edu.ucne.james_urena_ap2_p1.data.local.entity.BorrameEntity

@Dao
interface Dao {
    suspend fun upsert(entity: BorrameEntity)
}