package br.com.alura.anyflix.controllers

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
        val savedMovie = service.save(
            request.toMovie()
        )
        return ResponseEntity.ok(
            savedMovie.toResponse()
        )
    }

    @PutMapping("{id}")
    fun update(
        @RequestBody request: MovieRequest,
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> {
        val savedMovie = service.save(
            request.toMovie(id)
        )
        return ResponseEntity.ok(
            savedMovie
        )
    }

    @DeleteMapping("{id}")
    fun remove(
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        try {
            service.remove(id)
            ResponseEntity.ok().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

    @PutMapping("addToMyList/{id}")
    fun addToMyList(
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        if (service.existsById(id)) {
            service.addToMyList(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }

    @PutMapping("removeFromMyList/{id}")
    fun removeFromMyList(
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        if (service.existsById(id)) {
            service.removeFromMyList(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }

}

