package edu.ucne.james_urena_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable
sealed class Screen {
    @Serializable
    data object BorrameList : Screen()

    @Serializable
    data class Borrame(val borrameId: Int) : Screen()
}