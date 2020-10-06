package com.dangerfield.hiltplayground.ui.home

import java.util.*

open class IhrViewItem(val id: String = UUID.randomUUID().toString()) {

    fun isSameItem(itemIhr: IhrViewItem) : Boolean {
        return itemIhr.id == this.id
    }
}