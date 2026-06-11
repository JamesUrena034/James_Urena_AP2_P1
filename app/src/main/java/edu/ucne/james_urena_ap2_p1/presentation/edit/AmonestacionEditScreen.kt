package edu.ucne.james_urena_ap2_p1.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionEditScreen(
    amonestacionId: Int?,
    onNavigateBack: () -> Unit,
    viewModel: AmonestacionEditViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(amonestacionId) {
        if (amonestacionId != null && amonestacionId != 0) {
            viewModel.onEvent(AmonestacionEditUiEvent.Load(amonestacionId))
        }
    }

    LaunchedEffect(state.saved, state.deleted) {
        if (state.saved || state.deleted) {
            onNavigateBack()
        }
    }

    AmonestacionEditBody(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmonestacionEditBody(
    state: AmonestacionEditUiState,
    onEvent: (AmonestacionEditUiEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (state.isNew) "Nueva Amonestacion" else "Editar Amonestacion") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = state.nombres,
                        onValueChange = { onEvent(AmonestacionEditUiEvent.NombresChanged(it)) },
                        label = { Text("Nombres") },
                        isError = state.nombresError != null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    state.nombresError?.let {
                        Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.razon,
                        onValueChange = { onEvent(AmonestacionEditUiEvent.RazonChanged(it)) },
                        label = { Text("Razon") },
                        isError = state.razonError != null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    state.razonError?.let {
                        Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.monto,
                        onValueChange = { onEvent(AmonestacionEditUiEvent.MontoChanged(it)) },
                        label = { Text("Monto") },
                        prefix = { Text("$ ") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = state.montoError != null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    state.montoError?.let {
                        Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }

                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (!state.isNew) {
                            OutlinedButton(
                                onClick = { onEvent(AmonestacionEditUiEvent.Delete) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                                enabled = !state.isDeleting
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = null)
                                Spacer(Modifier.width(4.dp))
                                Text("Eliminar")
                            }
                        }

                        Button(
                            onClick = { onEvent(AmonestacionEditUiEvent.Save) },
                            modifier = Modifier.weight(1f),
                            enabled = !state.isSaving
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(Modifier.width(4.dp))
                            Text("Guardar")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmonestacionEditPreview() {
    MaterialTheme {
        AmonestacionEditBody(
            state = AmonestacionEditUiState(
                nombres = "James Urena",
                razon = "Llegada tardía",
                monto = "500.0",
                isNew = false
            ),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}