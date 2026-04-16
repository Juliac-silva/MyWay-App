package com.example.myway

import androidx.annotation.DrawableRes

data class Local(
    val nome: String,
    val endereco: String,
    val categoria: String,
    @DrawableRes val imagemRes: Int
)