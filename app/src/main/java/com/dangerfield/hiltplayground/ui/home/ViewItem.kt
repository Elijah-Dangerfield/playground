package com.dangerfield.hiltplayground.ui.home

import java.util.*

open class ViewItem(val id: String = UUID.randomUUID().toString()) {

    fun isSameItem(item: ViewItem) : Boolean {
        return item.id == this.id
    }
}