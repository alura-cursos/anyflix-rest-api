package br.com.alura.anyflix.dto

import br.com.alura.anyflix.models.Movie

data class MovieResponse(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val plot: String,
    val inMyList: Boolean
)


