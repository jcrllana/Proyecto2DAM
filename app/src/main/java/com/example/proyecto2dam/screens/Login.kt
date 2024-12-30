package com.example.proyecto2dam.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun Login (navController: NavHostController, auth: FirebaseAuth) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(Modifier.height(32.dp))

        Text(text = "Email", color = White, fontSize = 24.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )


        Spacer(Modifier.height(16.dp))

        Text(text = "Contraseña", color = White, fontSize = 24.sp)
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@Button // Salimos del bloque de código del botón
            }

            //si está relleno, intentamos iniciar sesión
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    // Si el inicio de sesión ha sido correcto, navegamos a la pantalla de Home
                    if (task.isSuccessful) {
                        navController.navigate("Home")
                        Log.i("Jc", "Inicio de sesión correcto")
                    } else {

                        // Si no se ha podido iniciar sesión, mostramos un mensaje de error
                        val errorMsg = task.exception?.message ?: "Error desconocido"
                        Toast.makeText(context, "Inicio de sesión fallido: $errorMsg", Toast.LENGTH_SHORT).show()
                        Log.e("Jc", errorMsg)
                    }
                }
        }) {
            Text(text = "Login")
        }
    }

}