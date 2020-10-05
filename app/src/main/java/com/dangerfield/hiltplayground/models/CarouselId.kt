package com.dangerfield.hiltplayground.models

data class CarouselId(val id: String)

fun String.toCarouselId() =
    CarouselId(this)