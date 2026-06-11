package edu.ucne.james_urena_ap2_p1.presentation.list

import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion

data class AmonestacionListUiState(
    val amonestaciones: List<Amonestacion> = emptyList(),
    val isLoading: Boolean = false,
    val conteo: Int = 0,
    val totalMonto: Double = 0.0
)