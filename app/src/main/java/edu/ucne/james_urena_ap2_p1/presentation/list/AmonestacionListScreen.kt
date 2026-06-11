package edu.ucne.james_urena_ap2_p1.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.james_urena_ap2_p1.domain.model.Amonestacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionListScreen(
    goToAmonestacion: (Int) -> Unit,
    createAmonestacion: () -> Unit,
    viewModel: AmonestacionListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AmonestacionListBody(
        state = state,
        goToAmonestacion = goToAmonestacion,
        createAmonestacion = createAmonestacion,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmonestacionListBody(
    state: AmonestacionListUiState,
    goToAmonestacion: (Int) -> Unit,
    createAmonestacion: () -> Unit,
    onEvent: (AmonestacionListUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Amonestaciones") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createAmonestacion) {
                Icon(Icons.Default.Add, contentDescription = "Crear")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp)
        ) {
            if (state.amonestaciones.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay amonestaciones registradas")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.amonestaciones) { amonestacion ->
                        AmonestacionRow(
                            amonestacion = amonestacion,
                            onEdit = { goToAmonestacion(amonestacion.amonestacionId ?: 0) }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total: ${state.conteo}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Monto total: $${"%,.2f".format(state.totalMonto)}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmonestacionRow(
    amonestacion: Amonestacion,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onEdit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = amonestacion.nombres,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Razon: ${amonestacion.razon}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Monto: $${"%,.2f".format(amonestacion.monto)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmonestacionListPreview() {
    MaterialTheme {
        AmonestacionListBody(
            state = AmonestacionListUiState(
                amonestaciones = listOf(
                    Amonestacion(1, "James Urena", "Llegada tardía", 500.0),
                    Amonestacion(2, "Ashley Urena", "Ausencia injustificada", 1000.0)
                ),
                conteo = 2,
                totalMonto = 1500.0
            ),
            goToAmonestacion = {},
            createAmonestacion = {},
            onEvent = {}
        )
    }
}