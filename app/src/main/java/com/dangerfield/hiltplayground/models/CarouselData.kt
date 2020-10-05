package com.dangerfield.hiltplayground.models

import com.dangerfield.hiltplayground.util.mtadapter.Items

//TODO add type saftey
class CarouselData @JvmOverloads constructor(data: List<*>,
                                             val carouselId: CarouselId? = null) {

    val data: Items = Items().add(data)
}