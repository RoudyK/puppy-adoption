package com.example.androiddevchallenge.data

data class Puppy(
    val id: String,
    val name: String,
    val age: String,
    val breed: String,
    val imageUrl: String,
    val about: About,
    val fee: Int,
    val health: Health,
    val location: Location
) {

    data class About(
        val short: String,
        val long: String
    )

    data class Health(
        val deSexed: Boolean,
        val vaccinated: Boolean,
        val wormed: Boolean
    )

    data class Location(
        val lat: Double,
        val lng: Double
    )
}
