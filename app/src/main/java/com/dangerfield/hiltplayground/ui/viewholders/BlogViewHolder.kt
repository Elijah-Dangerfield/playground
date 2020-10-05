package com.dangerfield.hiltplayground.ui.viewholders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.util.inflate
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(blogData: BlogData) {
        itemView.apply {
            blogTitle.text = blogData.title
            blogPreview.text = blogData.body
            blogCategory.text = "category: ${blogData.category}"

            Glide.with(blogImage.context)
                .load(blogData.image)
                .centerCrop()
                .into(blogImage)
        }
    }

    companion object {
        @JvmStatic
        fun create(view: ViewGroup): BlogViewHolder {
            return BlogViewHolder(
                view.inflate(R.layout.item_blog)
            )
        }
    }
}