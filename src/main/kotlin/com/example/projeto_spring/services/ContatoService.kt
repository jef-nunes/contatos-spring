package com.example.projeto_spring.services


import com.example.projeto_spring.api.dto.request.ContatoRequestDto
import com.example.projeto_spring.exceptions.CustomInvalidArgumentException
import com.example.projeto_spring.exceptions.CustomResourceNotFoundException
import com.example.projeto_spring.models.entities.Contato
import com.example.projeto_spring.models.entities.Tag
import com.example.projeto_spring.repositories.ContatoRepository
import com.example.projeto_spring.repositories.TagRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cglib.core.Local
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ContatoService(
    val contatoRepository: ContatoRepository,
    val tagRepository: TagRepository
) {

    // Logger
    private val logger: Logger = LoggerFactory.getLogger(ContatoService::class.java.getName())

    /*
    *       CRUD
   */

    // Listar
    fun findAll(): List<Contato> {
        logger.info("Listando todos os contatos.")
        return contatoRepository.findAll()
    }

    // Encontrar
    fun find(id: Long): Contato {
        logger.info("Encontrando contato com ID=$id.")
        return contatoRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }
    }

    // Criar
    fun create(requestDto: ContatoRequestDto): Contato {

        logger.info("Criando um contato.")

        val entity = Contato()

        entity.nome = requestDto.nome
        entity.email = requestDto.email
        entity.telefone = requestDto.telefone
        entity.dataCriado = LocalDateTime.now()
        entity.dataModificado = LocalDateTime.now()

        return contatoRepository.save(entity)
    }

    // Atualizar
    fun update(id: Long, requestDto: ContatoRequestDto): Contato? {

        if(id<=0){
            throw CustomInvalidArgumentException()
        }

        logger.info("Atualizando contato com ID=$id.")

        val entity: Contato = contatoRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }
        entity.nome = requestDto.nome
        entity.email = requestDto.email
        entity.telefone = requestDto.telefone
        entity.dataModificado = LocalDateTime.now()

        return contatoRepository.save(entity)
    }

    // Remover
    fun delete(id: Long) {

        if(id<=0){
            throw CustomInvalidArgumentException()
        }

        logger.info("Removendo contato com ID=$id.")

        contatoRepository.findById(id).orElseThrow {
            CustomResourceNotFoundException()
        }

        contatoRepository.deleteById(id)
    }


    /*
    *       Relacionamentos
   */
    // Adicionar Tag
    fun addTag(contatoId: Long, tagId: Long): Contato {
        logger.info("Adicionando tag com ID=$tagId ao contato com ID=$contatoId.")
        val contato: Contato = contatoRepository.findById(contatoId).orElseThrow {
            CustomResourceNotFoundException()
        }
        val tag: Tag = tagRepository.findById(tagId).orElseThrow {
            CustomResourceNotFoundException()
        }

        contato.tags.add(tag)
        tag.contatos.add(contato)

        contatoRepository.save(contato)
        tagRepository.save(tag)

        return contato
    }

    // Remover Tag
    fun removeTag(contatoId: Long, tagId: Long): Contato {

        logger.info("Removendo tag com ID=$tagId do contato com ID=$contatoId.")

        val contato: Contato = contatoRepository.findById(contatoId).orElseThrow {
            CustomResourceNotFoundException()
        }

        val tag: Tag = tagRepository.findById(tagId).orElseThrow {
            CustomResourceNotFoundException()
        }

        contato.tags.remove(tag)
        tag.contatos.remove(contato)

        contatoRepository.save(contato)
        tagRepository.save(tag)

        return contato
    }
}