package br.com.alura.anyflix.repositories

import br.com.alura.anyflix.models.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository : CrudRepository<Movie, UUID>