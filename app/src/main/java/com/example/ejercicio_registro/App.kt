package com.example.ejercicio_registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(userViewModel: UserViewModel = viewModel()) {
    var showList by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Fitness JD") },
                actions = {
                    IconButton(onClick = { showList = !showList }) {
                        Icon(Icons.Default.List, contentDescription = "Mostrar lista")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showList = !showList }) {
                Icon(Icons.Default.List, contentDescription = "Mostrar lista")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1E1E2F))) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(R.drawable.fitness),
                    contentDescription = "Portada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                if (showList) {
                    UserListScreen(userViewModel) { user ->
                        // Maneja la edición aquí
                        RegistroScreen(userViewModel = userViewModel, user = user) // Asegúrate de que este sea el correcto
                    }
                } else {
                    RegistroScreen(userViewModel) // Pantalla para registrar nuevos usuarios
                }
            }
        }
    }
}
