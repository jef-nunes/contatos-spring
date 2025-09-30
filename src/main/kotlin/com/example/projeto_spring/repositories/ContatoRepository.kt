package com.example.projeto_spring.repositories

import com.example.projeto_spring.models.entities.Contato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContatoRepository: JpaRepository<Contato, Long> {
}