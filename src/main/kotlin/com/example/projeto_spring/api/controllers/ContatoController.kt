package com.example.projeto_spring.api.controllers

import com.example.projeto_spring.api.controllers.util.ResponseBuilder
import com.example.projeto_spring.api.dto.map.ToResponseDto
import com.example.projeto_spring.api.dto.request.ContatoRequestDto
import com.example.projeto_spring.api.dto.response.ContatoResponseDto
import com.example.projeto_spring.models.ResponseModel
import com.example.projeto_spring.models.entities.Contato
import com.example.projeto_spring.services.ContatoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contatos")
class ContatoController(
    private val contatoService: ContatoService
) {
    @GetMapping
    fun findAll(): ResponseEntity<ResponseModel<ContatoResponseDto>> {
        val entityList: List<Contato> = contatoService.findAll()
        val responseDtoList: MutableList<ContatoResponseDto> = mutableListOf()
        entityList.forEach {
                entity ->
            val responseDto: ContatoResponseDto = ToResponseDto.fromContatoEntity(entity)
            responseDtoList.add(responseDto)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(responseDtoList))
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long):  ResponseEntity<ResponseModel<Any>>  {
        val entity: Contato = contatoService.find(id)
        val responseDto: ContatoResponseDto = ToResponseDto.fromContatoEntity(entity)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
    }

    @PostMapping
    fun create(@Valid @RequestBody requestDto: ContatoRequestDto):  ResponseEntity<ResponseModel<Any>>  {
        val entity = contatoService.create(requestDto)
        val responseDto: ContatoResponseDto = ToResponseDto.fromContatoEntity(entity)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody requestDto: ContatoRequestDto
    ): ResponseEntity<ResponseModel<Any>> {
        val entity = contatoService.update(id, requestDto)
        if(entity!=null) {
            val responseDto: ContatoResponseDto = ToResponseDto.fromContatoEntity(entity)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
        }
        else{
            val errorMsg = "Ocorreu um erro ao atualizar o contato."
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.buildFailureResponse(mutableListOf(errorMsg)))
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long):  ResponseEntity<ResponseModel<ContatoResponseDto>>  {
        contatoService.delete(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse())
    }

    @PostMapping("/{contatoId}/vincular-tag/{tagId}")
    fun addTag(@PathVariable contatoId: Long, @PathVariable tagId: Long): ResponseEntity<ResponseModel<Any>> {
        val entity: Contato = contatoService.addTag(contatoId,tagId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(entity)))
    }

    @DeleteMapping("/{contatoId}/vincular-tag/{tagId}")
    fun removeTag(@PathVariable contatoId: Long, @PathVariable tagId: Long): ResponseEntity<ResponseModel<Any>> {
        val entity: Contato = contatoService.removeTag(contatoId,tagId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(entity)))
    }
}