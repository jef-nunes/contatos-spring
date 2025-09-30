package com.example.projeto_spring.api.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class TagRequestDto(

    @field:NotBlank(message = "O nome é  obrigatório.")
    @field:Size(min=1,max=255, message = "O nome deve ter entre 1 e 255 caracteres")
    var nome: String,

    @field:Size(min=1,max=255, message = "A descrição deve ter entre 1 e 255 caracteres")
    var descricao: String
)
