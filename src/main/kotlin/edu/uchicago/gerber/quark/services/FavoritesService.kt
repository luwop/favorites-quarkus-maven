package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Favorites
import edu.uchicago.gerber.quark.repository.MongoFavoritesRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class FavoritesService {

    @Inject
    lateinit var repository: MongoFavoritesRepository

    fun create(favorites: Favorites): Favorites {
        return repository._create(favorites)
    }

    fun readById(id: String, sessionEmail: String): Favorites {
        return repository._readById(id, sessionEmail)
    }

    fun readAll(sessionEmail: String): List<Favorites> {
        return repository._readAll(sessionEmail)
    }

    fun update(favorites: Favorites): Favorites {
        return repository._update(favorites)
    }

    fun deleteById(id: String, sessionEmail: String): Favorites {
        return repository._deleteById(id, sessionEmail)
    }

    fun deleteAll(sessionEmail: String): List<Favorites> {
        return repository._deleteAll(sessionEmail)
    }

    fun findAll(sessionEmail: String): PanacheQuery<Favorites> {
        return repository._findAll(sessionEmail)
    }

    fun count(sessionEmail: String): Long {
        return repository._count(sessionEmail)
    }
}
