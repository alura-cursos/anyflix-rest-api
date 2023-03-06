package br.com.alura.anyflix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class AnyflixApplication

fun main(args: Array<String>) {
	runApplication<AnyflixApplication>(*args)
}
