package br.com.alura.anyflix.dto

import br.com.alura.anyflix.models.Movie

data class MovieResponse(
    val id: String,
    val title: String,
    val image: String,
    val year: String,
    val plot: String,
    val inMyList: Boolean
)

fun Movie.toResponse() = MovieResponse(
    id = id.toString(),
    title = title,
    image = image,
    year = year,
    plot = plot,
    inMyList = inMyList
)
