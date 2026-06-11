package edu.ucne.james_urena_ap2_p1.domain.usecase

import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import edu.ucne.james_urena_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Unit> {

        val nombresResult = validateNombres(amonestacion.nombres)
        if (!nombresResult.isValid)
            return Result.failure(IllegalArgumentException("nombres|${nombresResult.error}"))

        val razonResult = validateRazon(amonestacion.razon)
        if (!razonResult.isValid)
            return Result.failure(IllegalArgumentException("razon|${razonResult.error}"))

        val montoResult = validateMonto(amonestacion.monto.toString())
        if (!montoResult.isValid)
            return Result.failure(IllegalArgumentException("monto|${montoResult.error}"))

        return runCatching { repository.save(amonestacion) }
    }
}