package br.com.alura.anyflix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnyflixApplication

fun main(args: Array<String>) {
	runApplication<AnyflixApplication>(*args)
}
