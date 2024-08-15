package edu.uchicago.gerber.quark.repository

import edu.uchicago.gerber.quark.models.Favorites
import edu.uchicago.gerber.quark.models.Faked
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.ws.rs.ClientErrorException
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import org.bson.types.ObjectId

@ApplicationScoped
class MongoFavoritesRepository : PanacheMongoRepository<Favorites> {

    fun onStart(@Observes ev: StartupEvent?) {
        if (count() == 0L) {
            val list = mutableListOf<Favorites>()
            repeat(1000) { list.add(Faked.genRawEntity()) }
            persist(list)

            persist(Faked.genTestFavorites(Faked.FAKE_ID))
        }
    }

    fun _create(favorites: Favorites): Favorites {
        val exists = find(
            "sessionEmail = ?1 and (name = ?2 or url = ?4)",
            favorites.sessionEmail,
            favorites.name,
            favorites.url
        ).count() > 0

        if (exists) {
            throw ClientErrorException("A favorite already exists.", Response.Status.CONFLICT)
        }

        this.persist(favorites)
        return favorites
    }

    fun _create(favoritesList: List<Favorites>): List<Favorites> {
        this.persist(favoritesList)
        return listAll()
    }

    fun _readById(id: String, sessionEmail: String): Favorites {
        val favoritesId = ObjectId(id)
        val existingFavorites = this.find("id = ?1 and sessionEmail = ?2", favoritesId, sessionEmail).firstResult()
        return existingFavorites ?: throw NotFoundException()
    }

    fun _readAll(sessionEmail: String): List<Favorites> {
        val favorites = this.find("sessionEmail", sessionEmail).list()
        if (favorites.isEmpty()) {
            throw NotFoundException()
        }
        return favorites
    }

    fun _update(updatedFavorites: Favorites): Favorites {
        val exists = find(
            "sessionEmail = ?1 and (name = ?2 or url = ?3) and id != ?4",
            updatedFavorites.sessionEmail,
            updatedFavorites.name,
            updatedFavorites.id
        ).count() > 0

        if (exists) {
            throw ClientErrorException("A favorite already exists with similar details.", Response.Status.CONFLICT)
        }

        this.update(updatedFavorites)
        return updatedFavorites
    }

    fun _deleteById(id: String, sessionEmail: String): Favorites {
        val favorites = _readById(id, sessionEmail)
        this.deleteById(ObjectId(id))
        return favorites
    }

    fun _deleteAll(sessionEmail: String): List<Favorites> {
        val favorites = _readAll(sessionEmail)
        this.delete("sessionEmail", sessionEmail)
        return favorites
    }

    fun _count(sessionEmail: String): Long {
        return this.count("sessionEmail", sessionEmail)
    }

    fun _findAll(sessionEmail: String): PanacheQuery<Favorites> {
        return this.find("sessionEmail", sessionEmail)
    }
}
