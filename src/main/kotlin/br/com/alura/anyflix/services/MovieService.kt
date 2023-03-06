package br.com.alura.anyflix.services

import br.com.alura.anyflix.models.Movie
import br.com.alura.anyflix.repositories.MovieRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(
    private val repository: MovieRepository
) {

    fun findAll() =
        repository.findAll().toList()

    fun save(movie: Movie): Movie =
        repository.save(movie)

    fun existsById(id: UUID): Boolean =
        repository.existsById(id)

    fun remove(id: UUID) {
        repository.deleteById(id)
    }

    fun addToMyList(id: UUID) {
        repository.addToMyList(id)
    }

    fun removeFromMyList(id: UUID) {
        repository.removeFromMyList(id)
    }

}