package com.example.surgeryskillapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.surgeryskillapp.ui.Screens.HomeScreen
import com.example.surgeryskillapp.ui.theme.SurgerySkillAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Makes the app look modern (full screen)
        setContent {
            SurgerySkillAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // --- 1. Login Screen ---
        composable("login") {
            LoginScreen(
                onNavigateToSignUp = {
                    // Switch to Sign Up
                    navController.navigate("signup") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onLoginSuccess = {
                    // Navigate to Home with a default name since Login doesn't ask for a name
                    navController.navigate("home/Trainee") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // --- 2. Sign Up Screen ---
        composable("signup") {
            SignUpScreen(
                onNavigateToLogin = {
                    // Switch back to Login
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onSignUpSuccess = { nameFromInput ->
                    // Navigate to Home AND pass the name user typed
                    // If user typed empty string, we default to "Trainee"
                    val finalName = if (nameFromInput.isNotBlank()) nameFromInput else "Trainee"

                    navController.navigate("home/$finalName") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        // --- 3. Home Screen (Receives the Name) ---
        composable(
            route = "home/{userName}", // Define the placeholder
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            // Extract the name from the arguments
            val name = backStackEntry.arguments?.getString("userName") ?: "Trainee"

            // Pass it to the screen
            HomeScreen(userName = name)
        }
    }
}