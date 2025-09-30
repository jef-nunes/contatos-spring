package com.example.projeto_spring.api.controllers.handlers

import com.example.projeto_spring.api.controllers.util.ResponseBuilder.buildFailureResponse
import com.example.projeto_spring.exceptions.CustomInvalidArgumentException
import com.example.projeto_spring.exceptions.CustomResourceNotFoundException
import com.example.projeto_spring.models.ResponseModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.List

// Gerencia exceções retornando uma response adequada ao client
@RestControllerAdvice
class GlobalExceptionResponseHandler {

    // Erros de validação (Jakarta Validation)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ResponseModel<*>?> {
        // Lista de mensagens de erro
        val errors: MutableList<String?> = ArrayList<String?>()
        // As mensagens de erro são providenciadas pelo Jakarta Validation
        val fieldErrors = ex.bindingResult.fieldErrors
        for (fieldError in fieldErrors) {
            errors.add(fieldError.defaultMessage)
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body<ResponseModel<*>?>(buildFailureResponse(errors))
    }

    // MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException?): ResponseEntity<ResponseModel<*>?> {
        val errorMsg = "URL da requisição é inválida."
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body<ResponseModel<*>?>(buildFailureResponse(List.of<String?>(errorMsg)))
    }

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException?): ResponseEntity<ResponseModel<*>?> {
        val errorMsg = "URL da requisição é inválida."
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body<ResponseModel<*>?>(buildFailureResponse(List.of<String?>(errorMsg)))
    }

    // Exceções personalizadas

    // CustomInvalidArgumentException
    @ExceptionHandler(CustomInvalidArgumentException::class)
    fun handleCustomInvalidArgumentException(Ex: CustomInvalidArgumentException?): ResponseEntity<ResponseModel<*>?>{
        val errorMsg = "O argumento fornecido é inválido."
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body<ResponseModel<*>?>(buildFailureResponse(List.of<String?>(errorMsg)))
    }


    // CustomResourceNotFoundException
    @ExceptionHandler(CustomResourceNotFoundException::class)
    fun handleCustomResourceNotFoundException(Ex: CustomResourceNotFoundException?): ResponseEntity<ResponseModel<*>?>{
        val errorMsg = "O registro não foi encontrado."
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body<ResponseModel<*>?>(buildFailureResponse(List.of<String?>(errorMsg)))
    }


    // Qualquer outra exceção
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception?): ResponseEntity<ResponseModel<*>?> {
        val errorMsg = "Erro interno do servidor."
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body<ResponseModel<*>?>(buildFailureResponse(List.of<String?>(errorMsg)))
    }
}