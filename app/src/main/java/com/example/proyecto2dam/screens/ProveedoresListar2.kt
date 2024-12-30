package com.example.proyecto2dam.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto2dam.viewmodel.ProveedoresListarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.runtime.livedata.*
import androidx.lifecycle.LiveData


@Composable
fun ProveedoresListar2(navController: NavHostController, auth: FirebaseAuth, ViewModel: ProveedoresListarViewModel) {

    //obtener la lista de proveedores de proveedoresListarViewModel


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
                val proveedores = ViewModel.getProveedoresViewModel()
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
                ProveedorItem2(proveedor)
            }
        }

    }
}

//Funcion para mostrar un item de la lista de proveedores
@Composable
fun ProveedorItem2(proveedor: Proveedor) {

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