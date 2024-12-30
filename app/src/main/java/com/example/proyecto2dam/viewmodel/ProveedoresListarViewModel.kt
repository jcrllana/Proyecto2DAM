package com.example.proyecto2dam.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto2dam.models.Proveedor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class ProveedoresListarViewModel: ViewModel() {

    suspend fun getProveedoresViewModel(): List<Proveedor> {
        return try {
            // Obtener instancia de Firebase Firestore
            val db = FirebaseFirestore.getInstance()

            //Almacenar el nombre de la colección
            var nombre_coleccion = "proveedores"

            val query = db.collection(nombre_coleccion).get().await()

            val proveedores = mutableListOf<Proveedor>()

            for (document in query.documents) {
                val proveedor = document.toObject(Proveedor::class.java)
                if (proveedor != null) {
                    proveedores.add(proveedor)
                }
            }
            query.toObjects(Proveedor::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }


}