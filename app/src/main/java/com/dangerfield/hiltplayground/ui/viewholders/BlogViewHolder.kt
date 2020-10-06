package com.dangerfield.hiltplayground.ui.viewholders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.util.inflate
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: BlogData) {
        itemView.apply {
            blogTitle.text = data.title
            blogPreview.text = data.body
            blogCategory.text = "category: ${data.category}"

            Glide.with(blogImage.context)
                .load(data.image)
                .centerCrop()
                .into(blogImage)
        }
    }

    companion object {
        fun create(view: ViewGroup): BlogViewHolder {
            return BlogViewHolder(
                view.inflate(R.layout.item_blog)
            )
        }
    }
}