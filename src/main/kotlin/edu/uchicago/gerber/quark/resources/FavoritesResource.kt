package edu.uchicago.gerber.quark.resources

import edu.uchicago.gerber.quark.models.Favorites
import edu.uchicago.gerber.quark.services.FavoritesService
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.inject.Inject
import kotlin.math.ceil

@Path("/favorites")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FavoritesResource {

    @Inject
    lateinit var favoritesService: FavoritesService

    val TOTAL_PER_PAGE = 20

    @POST
    fun create(favorites: Favorites): Favorites {
        return favoritesService.create(favorites)
    }

    @GET
    @Path("/email/{sessionEmail}")
    fun readAll(@PathParam("sessionEmail") sessionEmail: String): List<Favorites> {
        return favoritesService.readAll(sessionEmail)
    }

    @GET
    @Path("/{id}/email/{sessionEmail}")
    fun getFavorites(@PathParam("id") id: String, @PathParam("sessionEmail") sessionEmail: String): Favorites {
        return favoritesService.readById(id, sessionEmail)
    }

    @GET
    @Path("/paged/{page}/email/{sessionEmail}")
    fun paged(@PathParam("page") page: Int, @PathParam("sessionEmail") sessionEmail: String): List<Favorites>? {
        val pagedFavorites: PanacheQuery<Favorites> = favoritesService.findAll(sessionEmail)

        val totalPages = ceil(pagedFavorites.count().toDouble() / TOTAL_PER_PAGE).toInt()
        if (page < 1 || page > totalPages) {
            throw NotFoundException("Page $page not found.")
        }
        return pagedFavorites.page(page, TOTAL_PER_PAGE).list() ?: throw Exception()
    }

    @PUT
    @Path("/update/{id}/email/{sessionEmail}")
    fun update(@PathParam("id") id: String, @PathParam("sessionEmail") sessionEmail: String, updatedFavorites: Favorites): Favorites {
        val existingFavorites = favoritesService.readById(id, sessionEmail)

        existingFavorites.name = updatedFavorites.name
        existingFavorites.url = updatedFavorites.url

        return favoritesService.update(existingFavorites)
    }

    @DELETE
    @Path("/{id}/email/{sessionEmail}")
    fun delete(@PathParam("id") id: String, @PathParam("sessionEmail") sessionEmail: String): Favorites {
        return favoritesService.deleteById(id, sessionEmail)
    }

    @DELETE
    @Path("/email/{sessionEmail}")
    fun deleteAll(@PathParam("sessionEmail") sessionEmail: String): List<Favorites> {
        return favoritesService.deleteAll(sessionEmail)
    }
}
