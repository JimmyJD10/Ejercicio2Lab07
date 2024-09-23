package com.example.ejercicio_registro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("MutableCollectionMutableState")
@Composable
fun UserListScreen(userViewModel: UserViewModel, onEdit: @Composable (User) -> Unit) {
    val users by userViewModel.allUsers.collectAsState(initial = emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Lista de Usuarios", style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(users) { user ->
                UserItem(
                    user,
                    onDeleteUser = { userViewModel.delete(user) },
                    onEditUser = { onEdit(user) } // Llama directamente a onEdit
                )
            }
        }
    }
}

@Composable
fun UserItem(user: User, onDeleteUser: () -> Unit, onEditUser: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = user.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "Edad: ${user.edad}, Peso: ${user.peso}, Altura: ${user.altura}")
            }
            Row {
                IconButton(onClick = onEditUser) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Editar usuario ${user.nombre}"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre botones
                IconButton(onClick = onDeleteUser) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar usuario ${user.nombre}")
                }
            }
        }
    }
}
