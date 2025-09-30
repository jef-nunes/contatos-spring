package com.example.projeto_spring.api.dto.map

import com.example.projeto_spring.api.dto.response.ContatoResponseDto
import com.example.projeto_spring.api.dto.response.TagResponseDto
import com.example.projeto_spring.models.entities.Contato
import com.example.projeto_spring.models.entities.Tag

object ToResponseDto {

    fun fromContatoEntity(entity: Contato): ContatoResponseDto{

        val tagIdList: MutableList<Long> = mutableListOf()

        entity.tags.forEach {
            tagEntity ->
            tagIdList.add(tagEntity.id!!)
        }

        val responseDto = ContatoResponseDto(
            id = entity.id!!,
            nome = entity.nome,
            email = entity.email,
            telefone = entity.telefone,
            dataCriado = entity.dataCriado,
            dataModificado = entity.dataModificado,
            tagIdList = tagIdList
        )

        return responseDto
    }

    fun fromTagEntity(entity: Tag): TagResponseDto{

        val contatosIdList: MutableList<Long> = mutableListOf()

        entity.contatos.forEach {
            contatoEntity ->
            contatosIdList.add(contatoEntity.id!!)
        }

        val responseDto = TagResponseDto(
            id = entity.id!!,
            nome = entity.nome,
            descricao = entity.descricao,
            dataCriado = entity.dataCriado,
            dataModificado = entity.dataModificado,
            contatosIdList = contatosIdList
        )

        return responseDto
    }
}