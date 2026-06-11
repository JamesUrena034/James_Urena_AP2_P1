package edu.ucne.james_urena_ap2_p1.domain.usecase

import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import edu.ucne.james_urena_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class DeleteAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion) = repository.delete(amonestacion)
}