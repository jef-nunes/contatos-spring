package com.example.projeto_spring.models

import java.time.LocalDateTime

data class ResponseModel<T>(
    val status: String?,
    val data: MutableList<T>?,
    val messages: MutableList<String?>?,
    val timestamp: LocalDateTime?,
)