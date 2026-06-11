package edu.ucne.james_urena_ap2_p1.domain.usecase

import edu.ucne.james_urena_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(id: Int) = repository.getById(id)
}