package edu.uchicago.gerber.quark.repository

import edu.uchicago.gerber.quark.models.Favorites
import io.quarkus.mongodb.panache.kotlin.PanacheQuery

interface FavoritesRepoInterface {
    fun _create(favorites: Favorites)
    fun _create(favoritesList: List<Favorites>)
    fun _readById(id: String): Favorites?
    fun readAll(): List<Favorites>
    fun _update(favorites: Favorites)
    fun _count(): Long
    fun _deleteById(id: String): Favorites?
    fun _deleteAll(): List<Favorites>
    fun _findAll(): PanacheQuery<Favorites>
}
