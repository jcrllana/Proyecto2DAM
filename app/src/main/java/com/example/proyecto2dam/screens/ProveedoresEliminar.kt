package com.example.proyecto2dam.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProveedoresEliminar(NavController: NavController, auth: FirebaseAuth, viewModel: ViewModel) {
    Text(text ="Proveedores Eliminar")
}