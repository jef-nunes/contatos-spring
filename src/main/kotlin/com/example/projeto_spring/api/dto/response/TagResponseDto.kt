package com.example.projeto_spring.api.dto.response

import com.example.projeto_spring.models.entities.Contato
import java.time.LocalDateTime

data class TagResponseDto(
    val id: Long,

    val nome: String?,

    val descricao: String?,

    val dataCriado: LocalDateTime?,

    val dataModificado: LocalDateTime?,

    val contatosIdList: MutableList<Long>
)
