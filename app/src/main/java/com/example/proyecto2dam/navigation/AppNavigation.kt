package com.example.proyecto2dam.navigation

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto2dam.screens.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation (auth: FirebaseAuth) {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Login.ruta)
    {
        composable(AppScreens.Login.ruta) { Login(navigationController, auth)  }
        composable(AppScreens.Home.ruta) { Home(navigationController, auth, viewModel()) }
        composable(AppScreens.ProveedorAlta.ruta) { ProveedorAlta(navigationController, auth, viewModel()) }
        composable(AppScreens.ProveedoresEliminar.ruta) { ProveedoresEliminar(navigationController, auth, viewModel()) }
        composable(AppScreens.ProveedoresListar.ruta) { ProveedoresListar(navigationController, auth, viewModel()) }
        composable(AppScreens.ProveedoresListar2.ruta) { ProveedoresListar2(navigationController, auth, viewModel()) }
    }
}