package br.com.alura.anyflix.controllers

import br.com.alura.anyflix.dto.MovieRequest
import br.com.alura.anyflix.dto.MovieResponse
import br.com.alura.anyflix.dto.toMovie
import br.com.alura.anyflix.models.toMovieResponse
import br.com.alura.anyflix.services.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
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
                it.toMovieResponse()
            }
        )

    @GetMapping("{id}")
    fun findMovieById(
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        service.findMovieById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(
        @RequestBody request: MovieRequest
    ): ResponseEntity<MovieResponse> =
        ResponseEntity.ok(
            service.save(
                request.toMovie()
            ).toMovieResponse()
        )

    @PutMapping("{id}")
    fun update(
        @RequestBody request: MovieRequest,
        @PathVariable("id") id: UUID
    ): ResponseEntity<Any> =
        ResponseEntity.ok(
            service.save(
                request.toMovie(id)
            )
        )

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

    @GetMapping("myList")
    fun myList():
            ResponseEntity<List<MovieResponse>> =
        ResponseEntity.ok(
            service.findMyList()
                .map {
                    it.toMovieResponse()
                }
        )

}

