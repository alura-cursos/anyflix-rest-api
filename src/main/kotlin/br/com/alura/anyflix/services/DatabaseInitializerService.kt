package br.com.alura.anyflix.services

import br.com.alura.anyflix.models.Movie
import br.com.alura.anyflix.repositories.MovieRepository
import com.thedeanda.lorem.LoremIpsum
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class DatabaseInitializerService(
    private val repository: MovieRepository
) {

    @Value("\${movies.amount}")
    private var amount: Int? = null
    private val logger: Logger = LoggerFactory.getLogger(
        DatabaseInitializerService::class.java
    )

    fun saveMovies() {
        val lorem = LoremIpsum.getInstance()
        val total = repository.count()
        if (total > 0) {
            logger.info("Total of movies saved: $total")
            val amountOfMoviesThatNeedToBeSave = (amount ?: 20) - total
            logger.info("Saving $amountOfMoviesThatNeedToBeSave movies")
            repeat(amountOfMoviesThatNeedToBeSave.toInt()) {
                repository.save(
                    Movie(
                        title = lorem.getWords(2, 3),
                        image = "https://picsum.photos/${Random.nextInt(1920, 2560)}/${Random.nextInt(1080, 1440)}",
                        year = Random.nextInt(1900, 2023),
                        plot = lorem.getWords(10, 30)
                    )
                )
            }
        }
    }
}
