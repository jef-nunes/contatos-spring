package com.example.projeto_spring.repositories

import com.example.projeto_spring.models.entities.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Tag, Long> {
}