package com.example.proyecto2dam.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto2dam.models.Proveedor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProveedorViewModel: ViewModel() {

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private val _proveedores = MutableStateFlow<List<Proveedor>>(emptyList())
    val proveedores: StateFlow<List<Proveedor>> = _proveedores

    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    fun onCompletedFields(nif:String, nombre:String) {
        _nif.value = nif
        _nombre.value = nombre
        _isButtonEnable.value = enableButton(nif, nombre)
    }

    fun enableButton(nif:String, nombre:String) =
        nif.length >0 && nombre.length >0
}




