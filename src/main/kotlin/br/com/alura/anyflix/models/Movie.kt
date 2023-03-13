package br.com.alura.anyflix.models

import br.com.alura.anyflix.dto.MovieResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "movies")
data class Movie(
    @Id
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val image: String,
    @Column(name = "release_year")
    val year: Int,
    val plot: String,
    val inMyList: Boolean = false
)

fun Movie.toMovieResponse() = MovieResponse(
    id = id.toString(),
    title = title,
    image = image,
    year = year,
    plot = plot,
    inMyList = inMyList
)