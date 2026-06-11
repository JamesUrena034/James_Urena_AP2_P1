package edu.ucne.james_urena_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.james_urena_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import edu.ucne.james_urena_ap2_p1.domain.usecase.ObserveAmonestacionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(
    private val observeAmonestacionUseCase: ObserveAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AmonestacionListUiState())
    val state = _state.asStateFlow()

    init {
        getAmonestaciones()
    }

    fun onEvent(event: AmonestacionListUiEvent) {
        when (event) {
            is AmonestacionListUiEvent.Delete -> onDelete(event.amonestacion)
            else -> {}
        }
    }

    private fun getAmonestaciones() {
        viewModelScope.launch {
            observeAmonestacionUseCase().collect { lista ->
                _state.update {
                    it.copy(
                        amonestaciones = lista,
                        conteo = lista.size,
                        totalMonto = lista.sumOf { a -> a.monto }
                    )
                }
            }
        }
    }

    private fun onDelete(amonestacion: edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion) {
        viewModelScope.launch {
            deleteAmonestacionUseCase(amonestacion)
        }
    }
}