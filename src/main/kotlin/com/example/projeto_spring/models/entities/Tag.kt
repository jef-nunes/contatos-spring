package com.example.projeto_spring.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Tag(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    var nome: String? = "",

    var descricao: String? = "",

    var dataCriado: LocalDateTime? = LocalDateTime.now(),

    var dataModificado: LocalDateTime? = LocalDateTime.now(),

    @ManyToMany(mappedBy = "tags")
    var contatos: MutableList<Contato> = mutableListOf()
)
