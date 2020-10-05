package com.dangerfield.hiltplayground.data.user

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
) {
    companion object {
        val DEFAULT = Address("", Geo("", ""), "", "", "")
    }
}