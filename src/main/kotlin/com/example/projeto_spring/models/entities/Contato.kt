package com.example.projeto_spring.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.JoinColumn
import org.springframework.cglib.core.Local
import java.time.LocalDateTime

@Entity
data class Contato(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    var nome: String? = "",

    var email: String? = "",

    var telefone: String? = "",

    var dataCriado: LocalDateTime? = LocalDateTime.now(),

    var dataModificado: LocalDateTime? = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
    name="contato_tag",
    joinColumns = [JoinColumn(name = "contato_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "id")]
    )
    var tags: MutableList<Tag> = mutableListOf()
)
