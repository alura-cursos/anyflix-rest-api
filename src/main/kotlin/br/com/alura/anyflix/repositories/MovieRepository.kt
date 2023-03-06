package br.com.alura.anyflix.repositories

import br.com.alura.anyflix.models.Movie
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface MovieRepository : CrudRepository<Movie, UUID> {

    @Modifying
    @Query("UPDATE movies SET inMyList = true WHERE id = :id")
    fun addToMyList(@Param("id") id: UUID)

    @Modifying
    @Query("UPDATE movies SET inMyList = false WHERE id = :id")
    fun removeFromMyList(@Param("id") id: UUID)

}