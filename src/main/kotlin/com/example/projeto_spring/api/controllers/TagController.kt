package com.example.projeto_spring.api.controllers

import com.example.projeto_spring.api.controllers.util.ResponseBuilder
import com.example.projeto_spring.api.dto.map.ToResponseDto
import com.example.projeto_spring.api.dto.request.TagRequestDto
import com.example.projeto_spring.api.dto.response.TagResponseDto
import com.example.projeto_spring.models.ResponseModel
import com.example.projeto_spring.models.entities.Tag
import com.example.projeto_spring.services.TagService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService
) {
    @GetMapping
    fun findAll(): ResponseEntity<ResponseModel<TagResponseDto>> {
        val entityList: List<Tag> = tagService.findAll()
        val responseDtoList: MutableList<TagResponseDto> = mutableListOf()
        entityList.forEach {
                entity ->
            val responseDto: TagResponseDto = ToResponseDto.fromTagEntity(entity)
            responseDtoList.add(responseDto)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(responseDtoList))
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long):  ResponseEntity<ResponseModel<Any>>  {
        val entity: Tag = tagService.find(id)
        val responseDto: TagResponseDto = ToResponseDto.fromTagEntity(entity)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
    }

    @PostMapping
    fun create(@Valid @RequestBody requestDto: TagRequestDto):  ResponseEntity<ResponseModel<Any>>  {
        val entity = tagService.create(requestDto)
        val responseDto: TagResponseDto = ToResponseDto.fromTagEntity(entity)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody requestDto: TagRequestDto
    ): ResponseEntity<ResponseModel<Any>> {
        val entity = tagService.update(id, requestDto)
        if(entity!=null) {
            val responseDto: TagResponseDto = ToResponseDto.fromTagEntity(entity)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(mutableListOf(responseDto)))
        }
        else{
            val errorMsg = "Ocorreu um erro ao atualizar o tag."
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.buildFailureResponse(mutableListOf(errorMsg)))
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long):  ResponseEntity<ResponseModel<TagResponseDto>>  {
        tagService.delete(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseBuilder.buildSuccessResponse())
    }

}