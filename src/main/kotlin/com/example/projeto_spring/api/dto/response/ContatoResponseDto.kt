package com.example.projeto_spring.api.dto.response

import java.time.LocalDateTime

data class ContatoResponseDto(
    val id: Long,

    val nome: String?,

    val email: String?,

    val telefone: String?,

    val dataCriado: LocalDateTime?,

    val dataModificado: LocalDateTime?,

    val tagIdList: MutableList<Long>
)
