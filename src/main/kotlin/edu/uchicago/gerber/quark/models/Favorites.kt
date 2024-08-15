package edu.uchicago.gerber.quark.models

import org.bson.types.ObjectId

class Favorites {
    var id: ObjectId? = null
    lateinit var sessionEmail: String
    lateinit var name: String
    lateinit var alias: String
    lateinit var imageUrl: String
    var isClosed: Boolean = false
    lateinit var url: String
    var reviewCount: Int = 0
    lateinit var categories: List<String>
    var rating: Double = 0.0
    lateinit var coordinates: Coordinates
    lateinit var transactions: List<String>
    lateinit var price: String
    lateinit var location: Location
    lateinit var phone: String
    lateinit var displayPhone: String
    var distance: Double = 0.0
    lateinit var businessHours: List<String>
    lateinit var attributes: Map<String, String>
}

class Coordinates(var latitude: Double, var longitude: Double)

class Location(var address: String)
