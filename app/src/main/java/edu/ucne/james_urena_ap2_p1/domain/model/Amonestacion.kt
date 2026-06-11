package edu.ucne.james_urena_ap2_p1.domain.model

data class Amonestacion(
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0
)