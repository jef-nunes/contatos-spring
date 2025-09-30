package com.example.projeto_spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class DataFolderConfig {

    @Bean
    fun createDataFolder(): Boolean {
        val folder = File("./data")
        if(!folder.exists()){
            folder.mkdirs()
        }
        return true
    }

}