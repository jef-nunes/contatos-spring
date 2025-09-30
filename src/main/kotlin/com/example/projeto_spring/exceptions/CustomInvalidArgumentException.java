package com.example.projeto_spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class CustomInvalidArgumentException extends RuntimeException {
        public CustomInvalidArgumentException(){
            super("O argumento é inválido.");
        }
    }