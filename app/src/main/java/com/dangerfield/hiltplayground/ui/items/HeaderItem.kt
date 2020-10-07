package com.dangerfield.hiltplayground.ui.items

import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.HeaderData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_simple_header.*

class HeaderItem(private val headerData: HeaderData) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.headerTitle.text = headerData.title
    }

    override fun getLayout() = R.layout.layout_simple_header
}