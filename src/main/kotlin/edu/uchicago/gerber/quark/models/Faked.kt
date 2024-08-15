package edu.uchicago.gerber.quark.models

import com.github.javafaker.Faker
import org.bson.types.ObjectId

object Faked {
    val faker = Faker()
    val FAKE_ID = "5063114bd386d8fadbd6b004"

    // Generates a fake Favorites object
    fun genRawFavorites(): Favorites {
        val favorites = Favorites()
        favorites.id = ObjectId()
        favorites.alias = faker.company().buzzword()
        favorites.name = faker.company().name()
        favorites.imageUrl = faker.internet().url()
        favorites.isClosed = faker.bool().bool()
        favorites.url = faker.internet().url()
        favorites.reviewCount = faker.number().numberBetween(1, 1000)
        favorites.categories = listOf(faker.commerce().department()) // This assumes categories is a List<String>
        favorites.rating = faker.number().randomDouble(1, 1, 5)
        favorites.coordinates = Coordinates(faker.address().latitude().toDouble(), faker.address().longitude().toDouble())
        favorites.transactions = listOf("pickup", "delivery") // Example transactions
        favorites.price = faker.commerce().price(1.0, 100.0)
        favorites.location = Location(faker.address().fullAddress())
        favorites.phone = faker.phoneNumber().phoneNumber()
        favorites.displayPhone = faker.phoneNumber().cellPhone()
        favorites.distance = faker.number().randomDouble(2, 1, 100)
        favorites.businessHours = listOf("9:00 AM - 5:00 PM") // Example business hours
        favorites.attributes = mapOf("attributeKey" to "attributeValue") // Example attributes
        return favorites
    }

    // Generates a Favorites object with a session email
    fun genRawEntity(): Favorites {
        val fakeFavorites = genRawFavorites()
        fakeFavorites.sessionEmail = faker.internet().emailAddress()
        return fakeFavorites
    }

    fun genFakerFavorites(num: Int): List<Favorites> {
        val list = mutableListOf<Favorites>()
        repeat(num) { list.add(genRawEntity()) }
        return list
    }

    // Use a string such as "5063114bd386d8fadbd6b004"
    fun genTestFavorites(hash: String): Favorites {
        val favorites = genRawEntity()
        favorites.id = ObjectId(hash)
        return favorites
    }
}
