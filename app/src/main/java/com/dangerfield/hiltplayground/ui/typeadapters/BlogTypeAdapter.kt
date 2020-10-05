package com.dangerfield.hiltplayground.ui.typeadapters

import android.view.ViewGroup
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.ui.viewholders.BlogViewHolder
import com.dangerfield.hiltplayground.util.mtadapter.TypeAdapter

class BlogTypeAdapter : TypeAdapter<BlogData, BlogViewHolder>() {

    //TODO what are we using isMyData for? Do we need this?
    override fun isMyData(data: Any): Boolean {
        return data is BlogData
    }

    //is this the exact same blog we already have?
    //TODO can the super handle the id comparison?
    override fun isDataEqual(data1: BlogData, data2: BlogData): Boolean {
        return data1.id == data2.id
    }

    //does the diff need to make a change to the item? Have the contents changed?
    override fun areContentsSame(data1: BlogData, data2: BlogData): Boolean {
        return data1 == data2
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup): BlogViewHolder {
        return BlogViewHolder.create(
            viewGroup
        )
    }

    override fun onBindViewHolder(viewHolder: BlogViewHolder, data: BlogData) {
        viewHolder.bind(data)
    }
}