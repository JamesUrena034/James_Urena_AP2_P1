package edu.ucne.james_urena_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import edu.ucne.james_urena_ap2_p1.presentation.navigation.AmonestacionNavHost
import edu.ucne.james_urena_ap2_p1.ui.theme.James_Urena_AP2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            James_Urena_AP2_P1Theme {
                val navHostController = rememberNavController()
                AmonestacionNavHost(navHostController = navHostController)
            }
        }
    }
}