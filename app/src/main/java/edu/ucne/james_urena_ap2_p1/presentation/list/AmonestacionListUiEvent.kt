package edu.ucne.james_urena_ap2_p1.presentation.list

import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion

sealed interface AmonestacionListUiEvent {
    data object CreateNew : AmonestacionListUiEvent
    data class Edit(val id: Int) : AmonestacionListUiEvent
    data class Delete(val amonestacion: Amonestacion) : AmonestacionListUiEvent
}