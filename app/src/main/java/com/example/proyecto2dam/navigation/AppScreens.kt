package com.example.proyecto2dam.navigation

sealed class AppScreens (val ruta:String) {
    object Login: AppScreens("Login")
    object Home: AppScreens("Home")
    object Proveedores: AppScreens("Proveedores")
    object ProveedorAlta: AppScreens("ProveedorAlta")
    object ProveedoresEliminar: AppScreens("ProveedoresEliminar")
    object ProveedoresListar: AppScreens("ProveedoresListar")
    object ProveedoresListar2: AppScreens("ProveedoresListar2")
}