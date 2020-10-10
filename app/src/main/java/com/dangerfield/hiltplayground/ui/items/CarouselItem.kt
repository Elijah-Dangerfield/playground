package com.dangerfield.hiltplayground.ui.items

import com.dangerfield.hiltplayground.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_carousel_view.*

class CarouselItem(private val adapter: GroupAdapter<out GroupieViewHolder>) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            carousel_view.adapter = adapter
        }
    }

    override fun getLayout() = R.layout.layout_carousel_view
}