package br.com.alura.anyflix.dto

import br.com.alura.anyflix.models.Movie

class MovieResponse(
    val id: String,
    val title: String,
    val image: String
)

fun Movie.toResponse(): MovieResponse {
    return MovieResponse(
        id = id.toString(),
        title = title,
        image = image
    )
}
