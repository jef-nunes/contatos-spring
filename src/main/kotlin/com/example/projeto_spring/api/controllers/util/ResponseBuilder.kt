package com.example.projeto_spring.api.controllers.util

import com.example.projeto_spring.models.ResponseModel
import java.time.LocalDateTime


object ResponseBuilder {

    fun <T> buildSuccessResponse(data: MutableList<T> = mutableListOf()): ResponseModel<T> {
        return ResponseModel(ResponseModelStatus.SUCCESS, data, mutableListOf(), LocalDateTime.now())
    }

    fun buildFailureResponse(errors: MutableList<String?>?): ResponseModel<Any> {
        return ResponseModel(ResponseModelStatus.FAILURE, mutableListOf(), errors, LocalDateTime.now())
    }

    private object ResponseModelStatus {
        const val SUCCESS: String = "success"
        const val FAILURE: String = "failure"
    }

}