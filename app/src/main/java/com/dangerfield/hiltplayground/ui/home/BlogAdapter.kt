package com.dangerfield.hiltplayground.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.ui.viewholders.BlogViewHolder

class BlogAdapter : IhrAdapter<BlogViewHolder, BlogData>() {

    private var items = listOf<BlogData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun setItems(data: List<BlogData>) {
        items = data
        notifyDataSetChanged()
    }

    override fun isMyData(data: List<Any>): Boolean {
        return data.firstOrNull() is BlogData
    }
}