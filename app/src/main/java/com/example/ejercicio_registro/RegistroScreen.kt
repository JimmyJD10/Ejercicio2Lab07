package com.example.ejercicio_registro

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(userViewModel: UserViewModel, user: User? = null) {
    var nombre by remember { mutableStateOf(user?.nombre ?: "") }
    var edad by remember { mutableStateOf(user?.edad?.toString() ?: "") }
    var peso by remember { mutableStateOf(user?.peso?.toString() ?: "") }
    var altura by remember { mutableStateOf(user?.altura?.toString() ?: "") }
    var userId by remember { mutableStateOf(user?.id) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E2F))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.fitness),
            contentDescription = "Encabezado",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Edad") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = "Peso") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = "Altura") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val user = User(
                id = userId,
                nombre = nombre,
                edad = edad.toIntOrNull() ?: 0,
                peso = peso.toFloatOrNull() ?: 0f,
                altura = altura.toFloatOrNull() ?: 0f
            )
            scope.launch {
                if (userId == null) {
                    userViewModel.insert(user)
                } else {
                    userViewModel.update(user)
                }
                nombre = ""
                edad = ""
                peso = ""
                altura = ""
                userId = null
            }
        }) {
            Text(if (userId == null) "Registrar" else "Actualizar")
        }
    }
}
