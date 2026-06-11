package edu.ucne.james_urena_ap2_p1.presentation.edit

data class AmonestacionEditUiState(
    val isNew: Boolean = true,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val saved: Boolean = false,
    val deleted: Boolean = false,
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val nombresError: String? = null,
    val razon: String = "",
    val razonError: String? = null,
    val monto: String = "",
    val montoError: String? = null
)