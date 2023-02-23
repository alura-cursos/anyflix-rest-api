package br.com.alura.anyflix.controller

import br.com.alura.anyflix.dto.MovieRequest
import br.com.alura.anyflix.dto.MovieResponse
import br.com.alura.anyflix.dto.toMovie
import br.com.alura.anyflix.dto.toResponse
import br.com.alura.anyflix.services.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.lang.IllegalArgumentException
import java.util.*

@RequestMapping("movies")
@Controller
class MoviesController(
    private val service: MovieService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<MovieResponse>> =
        ResponseEntity.ok(
            service.findAll().map {
                it.toResponse()
            }
        )

    @PostMapping
    fun save(
        @RequestBody request: MovieRequest
    ): ResponseEntity<MovieResponse> {
        val movie = request.toMovie()
        val savedMovie = service.save(movie)
        return ResponseEntity.ok(
            savedMovie.toResponse()
        )
    }

    @PutMapping("{id}")
    fun update(
        @RequestBody request: MovieRequest,
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        if (service.existsById(id)) {
            println("updating the movie")
            val savedMovie = service.save(
                request.toMovie(id)
            )
            ResponseEntity.ok(
                savedMovie
            )
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping("{id}")
    fun remove(
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        try {
            val savedMovie = service.remove(id)
            ResponseEntity.ok(
                savedMovie
            )
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

}