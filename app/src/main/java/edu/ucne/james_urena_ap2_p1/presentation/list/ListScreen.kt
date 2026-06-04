package edu.ucne.james_urena_ap2_p1.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    createBorrame: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Listado") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createBorrame) {
                Icon(Icons.Default.Add, contentDescription = "Crear")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun BorrameListPreview() {
    MaterialTheme {
        ListScreen(createBorrame = {})
    }
}