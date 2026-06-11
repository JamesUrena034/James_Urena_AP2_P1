package edu.ucne.james_urena_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.james_urena_ap2_p1.presentation.edit.AmonestacionEditScreen
import edu.ucne.james_urena_ap2_p1.presentation.list.AmonestacionListScreen

@Composable
fun AmonestacionNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AmonestacionList
    ) {
        composable<Screen.AmonestacionList> {
            AmonestacionListScreen(
                goToAmonestacion = { id ->
                    navHostController.navigate(Screen.Amonestacion(id))
                },
                createAmonestacion = {
                    navHostController.navigate(Screen.Amonestacion(0))
                }
            )
        }
        composable<Screen.Amonestacion> {
            val args = it.toRoute<Screen.Amonestacion>()
            AmonestacionEditScreen(
                amonestacionId = args.amonestacionId,
                onNavigateBack = { navHostController.navigateUp() }
            )
        }
    }
}