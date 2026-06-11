package edu.ucne.james_urena_ap2_p1.data.mapper

import edu.ucne.james_urena_ap2_p1.data.local.entities.AmonestacionEntity
import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion

fun AmonestacionEntity.toDomain(): Amonestacion =
    Amonestacion(
        amonestacionId = amonestacionId,
        nombres = nombres,
        razon = razon,
        monto = monto
    )

fun Amonestacion.toEntity(): AmonestacionEntity =
    AmonestacionEntity(
        amonestacionId = amonestacionId,
        nombres = nombres,
        razon = razon,
        monto = monto
    )