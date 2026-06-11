package edu.ucne.james_urena_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Amonestaciones")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int? = 0,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0
)