package br.com.alura.anyflix.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Movie(
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(
        name = "uuid4",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val image: String
)