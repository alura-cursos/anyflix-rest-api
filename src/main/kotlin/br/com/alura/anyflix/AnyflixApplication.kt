package br.com.alura.anyflix

import br.com.alura.anyflix.services.DatabaseInitializerService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnyflixApplication

fun main(args: Array<String>) {
    runApplication<AnyflixApplication>(*args)
        .let { context ->
            val service = context
                .getBean(DatabaseInitializerService::class.java)
            service.saveMovies()
        }
}




