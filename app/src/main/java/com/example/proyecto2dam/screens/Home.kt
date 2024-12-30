package com.example.proyecto2dam.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto2dam.viewmodel.ProveedorViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home (navController: NavHostController, auth: FirebaseAuth, ViewModel: ProveedorViewModel) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Menú de la App", fontSize = 24.sp)

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.navigate("ProveedorAlta") }) {
            Text(text = "Alta de Proveedor")
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.navigate("ProveedoresELiminar") }) {
            Text(text = "Eliminar Proveedor")
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.navigate("ProveedoresListar") }) {
            Text(text = "Listar Proveedores")
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.navigate("ProveedoresListar2") }) {
            Text(text = "Listar Proveedores 2")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}