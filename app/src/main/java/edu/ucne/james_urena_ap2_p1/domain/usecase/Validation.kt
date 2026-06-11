package edu.ucne.james_urena_ap2_p1.domain.usecase

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombres(nombres: String): ValidationResult {
    return when {
        nombres.isBlank() -> ValidationResult(false, "El nombre no puede estar vacío")
        else -> ValidationResult(true)
    }
}

fun validateRazon(razon: String): ValidationResult {
    return when {
        razon.isBlank() -> ValidationResult(false, "La razon no puede estar vacía")
        else -> ValidationResult(true)
    }
}

fun validateMonto(monto: String): ValidationResult {
    return when {
        monto.isBlank() -> ValidationResult(false, "El monto no puede estar vacío")
        monto.toDoubleOrNull() == null -> ValidationResult(false, "El monto debe ser un número")
        monto.toDouble() <= 0 -> ValidationResult(false, "El monto debe ser mayor a 0")
        else -> ValidationResult(true)
    }
}