package com.example.projeto_spring.api.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ContatoRequestDto(

    @field:NotBlank(message = "O nome é  obrigatório.")
    var nome: String,

    @field:Size(min=1,max=255,message = "O campo email deve ter entre 1 a 255 caracteres.")
    var email: String,

    @field:Size(min=1,max=30,message = "O campo telefone deve ter entre 1 a 30 caracteres.")
    var telefone: String
)
