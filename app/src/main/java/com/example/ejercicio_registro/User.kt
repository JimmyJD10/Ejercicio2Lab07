package com.example.ejercicio_registro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val nombre: String,
    val edad: Int,
    val peso: Float,
    val altura: Float
)
