package com.example.projeto_spring.services

import com.example.projeto_spring.api.dto.request.TagRequestDto
import com.example.projeto_spring.exceptions.CustomInvalidArgumentException
import com.example.projeto_spring.exceptions.CustomResourceNotFoundException
import com.example.projeto_spring.models.entities.Tag
import com.example.projeto_spring.repositories.TagRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TagService(
    val tagRepository: TagRepository
) {
    // Logger
    private val logger: Logger = LoggerFactory.getLogger(TagService::class.java.getName())

    /*
    *       CRUD
   */

    // Listar
    fun findAll(): MutableList<Tag> {
        logger.info("Listando todos os tags.")
        return tagRepository.findAll()
    }

    // Encontrar
    fun find(id: Long): Tag {
        logger.info("Encontrando tag com ID=$id.")
        return tagRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }
    }

    // Criar
    fun create(requestDto: TagRequestDto): Tag {

        logger.info("Criando um tag.")

        val entity = Tag()

        entity.nome = requestDto.nome
        entity.descricao = requestDto.descricao
        entity.dataCriado = LocalDateTime.now()
        entity.dataModificado = LocalDateTime.now()

        return tagRepository.save(entity)
    }

    // Atualizar
    fun update(id: Long, requestDto: TagRequestDto): Tag? {

        if(id<=0){
            throw CustomInvalidArgumentException()
        }

        logger.info("Atualizando tag com ID=$id.")

        val entity: Tag = tagRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }
        entity.nome = requestDto.nome
        entity.descricao = requestDto.descricao
        entity.dataModificado = LocalDateTime.now()

        return tagRepository.save(entity)
    }

    // Remover
    fun delete(id: Long) {

        if(id<=0){
            throw CustomInvalidArgumentException()
        }

        logger.info("Removendo tag com ID=$id.")

        tagRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }

        tagRepository.deleteById(id)
    }

}