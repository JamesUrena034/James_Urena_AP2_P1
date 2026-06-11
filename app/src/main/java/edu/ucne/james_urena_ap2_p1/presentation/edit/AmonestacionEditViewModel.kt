package edu.ucne.james_urena_ap2_p1.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion
import edu.ucne.james_urena_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import edu.ucne.james_urena_ap2_p1.domain.usecase.GetAmonestacionUseCase
import edu.ucne.james_urena_ap2_p1.domain.usecase.UpsertAmonestacionUseCase
import edu.ucne.james_urena_ap2_p1.domain.usecase.validateNombres
import edu.ucne.james_urena_ap2_p1.domain.usecase.validateRazon
import edu.ucne.james_urena_ap2_p1.domain.usecase.validateMonto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionEditViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AmonestacionEditUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: AmonestacionEditUiEvent) {
        when (event) {
            is AmonestacionEditUiEvent.Load -> onLoad(event.id)
            is AmonestacionEditUiEvent.NombresChanged ->
                _state.update { it.copy(nombres = event.value, nombresError = null) }
            is AmonestacionEditUiEvent.RazonChanged ->
                _state.update { it.copy(razon = event.value, razonError = null) }
            is AmonestacionEditUiEvent.MontoChanged ->
                _state.update { it.copy(monto = event.value, montoError = null) }
            AmonestacionEditUiEvent.Save -> onSave()
            AmonestacionEditUiEvent.Delete -> onDelete()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) return
        viewModelScope.launch {
            getAmonestacionUseCase(id)?.let { amonestacion ->
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacion.amonestacionId,
                        nombres = amonestacion.nombres,
                        razon = amonestacion.razon,
                        monto = amonestacion.monto.toString()
                    )
                }
            }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            val nombresResult = validateNombres(_state.value.nombres)
            val razonResult = validateRazon(_state.value.razon)
            val montoResult = validateMonto(_state.value.monto)

            if (!nombresResult.isValid || !razonResult.isValid || !montoResult.isValid) {
                _state.update {
                    it.copy(
                        nombresError = nombresResult.error,
                        razonError = razonResult.error,
                        montoError = montoResult.error
                    )
                }
                return@launch
            }

            _state.update { it.copy(isSaving = true) }
            val amonestacion = Amonestacion(
                amonestacionId = _state.value.amonestacionId,
                nombres = _state.value.nombres,
                razon = _state.value.razon,
                monto = _state.value.monto.toDoubleOrNull() ?: 0.0
            )
            upsertAmonestacionUseCase(amonestacion)
                .onSuccess {
                    _state.update { it.copy(isSaving = false, saved = true) }
                }
                .onFailure { e ->
                    _state.update { it.copy(isSaving = false, nombresError = e.message) }
                }
        }
    }

    private fun onDelete() {
        val currentState = _state.value
        if (currentState.amonestacionId == null) return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            val amonestacion = Amonestacion(
                amonestacionId = currentState.amonestacionId,
                nombres = currentState.nombres,
                razon = currentState.razon,
                monto = currentState.monto.toDoubleOrNull() ?: 0.0
            )
            deleteAmonestacionUseCase(amonestacion)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}