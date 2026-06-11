package edu.ucne.james_urena_ap2_p1.presentation.edit

sealed interface AmonestacionEditUiEvent {
    data class Load(val id: Int?) : AmonestacionEditUiEvent
    data class NombresChanged(val value: String) : AmonestacionEditUiEvent
    data class RazonChanged(val value: String) : AmonestacionEditUiEvent
    data class MontoChanged(val value: String) : AmonestacionEditUiEvent
    data object Save : AmonestacionEditUiEvent
    data object Delete : AmonestacionEditUiEvent
}