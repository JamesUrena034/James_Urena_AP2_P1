package edu.ucne.james_urena_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.james_urena_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.james_urena_ap2_p1.data.local.entities.AmonestacionEntity

@Database(
    entities = [AmonestacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AmonestacionDb : RoomDatabase() {
    abstract fun amonestacionDao(): AmonestacionDao
}