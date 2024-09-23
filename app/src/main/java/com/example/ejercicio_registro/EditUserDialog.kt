package com.example.ejercicio_registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EditUserDialog(
    userViewModel: UserViewModel,
    user: User,
    onDismiss: () -> Unit
) {
    var nombre by remember { mutableStateOf(user.nombre) }
    var edad by remember { mutableStateOf(user.edad.toString()) }
    var peso by remember { mutableStateOf(user.peso.toString()) }
    var altura by remember { mutableStateOf(user.altura.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuario") },
        text = {
            Column {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Edad") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = peso,
                    onValueChange = { peso = it },
                    label = { Text("Peso") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = altura,
                    onValueChange = { altura = it },
                    label = { Text("Altura") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val updatedUser = user.copy(
                    nombre = nombre,
                    edad = edad.toInt(),
                    peso = peso.toFloat(),
                    altura = altura.toFloat()
                )
                userViewModel.insert(updatedUser)
                onDismiss()
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
