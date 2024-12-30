package com.example.proyecto2dam.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.proyecto2dam.models.Proveedor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto2dam.viewmodel.ProveedorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun ProveedoresListar(navController: NavHostController, auth: FirebaseAuth, ViewModel: ProveedorViewModel) {

     var listaProveedores by remember { mutableStateOf(emptyList<Proveedor>()) }

    //incluimos la vista
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top= 60.dp)
    ) {
        Text(text = "Listado de Proveedores", fontWeight = FontWeight.Bold)

        //Realizar una operacion asincrona
        DisposableEffect(true) {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val proveedores = getProveedores()

                listaProveedores = proveedores
            }
            onDispose {
                job.cancel()
            }
        }

        //LazyColumn para mostar la lista de proveedores

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(listaProveedores) { proveedor ->
                ProveedorItem(proveedor)
            }
        }

    }




}

//Funcion suspendida para obtener la lista de proveedores dentro de un try cath
suspend fun getProveedores(): List<Proveedor> {
    return try {
        // Obtener instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        //Almacenar el nombre de la colección
        var nombre_coleccion = "proveedores"

        val querySnapshot = db.collection(nombre_coleccion).get().await()

        val proveedores = mutableListOf<Proveedor>()

        for (document in querySnapshot.documents) {
            val proveedor = document.toObject(Proveedor::class.java)
            if (proveedor != null) {
                proveedores.add(proveedor)
            }
        }
        querySnapshot.toObjects(Proveedor::class.java)

    } catch (e: Exception) {
        emptyList()
    }
}

//Funcion para mostrar un item de la lista de proveedores
@Composable
fun ProveedorItem(proveedor: Proveedor) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "NIF: ${proveedor.nif}")
            Text(text = "Nombre: ${proveedor.nombre}")
        }
    }
}