package br.com.alura.anyflix.dto

import br.com.alura.anyflix.models.Movie
import java.util.UUID

data class MovieRequest(
    val title: String,
    val image: String
)

fun MovieRequest.toMovie(
    id: UUID? = null
) = Movie(
    id = id ?: UUID.randomUUID(),
    title = title,
    image = image
)