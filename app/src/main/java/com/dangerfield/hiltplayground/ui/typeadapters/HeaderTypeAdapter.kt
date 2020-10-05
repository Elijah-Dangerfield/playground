package com.dangerfield.hiltplayground.ui.typeadapters

import android.view.ViewGroup
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.ui.viewholders.HeaderViewHolder
import com.dangerfield.hiltplayground.util.mtadapter.TypeAdapter

class HeaderTypeAdapter : TypeAdapter<HeaderData, HeaderViewHolder>() {

    //TODO what are we using isMyData for? Do we need this?
    override fun isMyData(data: Any): Boolean {
        return data is HeaderData
    }

    //is this the exact same title we already have?
    //TODO can the super handle the id comparison?
    override fun isDataEqual(data1: HeaderData, data2: HeaderData): Boolean {
        return data1.id == data2.id
    }

    //does the diff need to make a change to the item? Have the contents changed?
    override fun areContentsSame(data1: HeaderData, data2: HeaderData): Boolean {
        return data1 == data2
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup): HeaderViewHolder {
        return HeaderViewHolder.create(
            viewGroup
        )
    }

    override fun onBindViewHolder(viewHolder: HeaderViewHolder, data: HeaderData) {
        viewHolder.bind(data)
    }
}