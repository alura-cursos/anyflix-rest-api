package br.com.alura.anyflix.dto

import br.com.alura.anyflix.models.Movie
import java.util.*

data class MovieRequest(
    val title: String,
    val image: String,
    val year: Int,
    val plot: String
)

fun MovieRequest.toMovie(
    id: UUID? = null
) = Movie(
    id = id ?: UUID.randomUUID(),
    title = title,
    image = image,
    plot = plot,
    year = year
)