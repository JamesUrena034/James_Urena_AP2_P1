package edu.ucne.james_urena_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.james_urena_ap2_p1.presentation.edit.EditScreen
import edu.ucne.james_urena_ap2_p1.presentation.list.ListScreen

@Composable
fun BorrameNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
            ListScreen(
                createBorrame = {
                    navHostController.navigate(Screen.Borrame(0))
                }
            )
        }
        composable<Screen.Borrame> {
            EditScreen(
                onNavigateBack = { navHostController.navigateUp() }
            )
        }
    }
}