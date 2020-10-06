package com.dangerfield.hiltplayground.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.ui.viewholders.HeaderViewHolder

class HeaderAdapter : IhrAdapter<HeaderViewHolder, HeaderData>() {

    private var items = listOf<HeaderData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun setItems(data: List<HeaderData>) {
        items = data
        notifyDataSetChanged()
    }

    override fun isMyData(data: List<Any>): Boolean {
        return data.firstOrNull() is HeaderData
    }
}