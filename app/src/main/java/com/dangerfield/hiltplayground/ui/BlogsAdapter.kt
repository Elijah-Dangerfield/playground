package com.dangerfield.hiltplayground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.Blog
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogsAdapter(val mCallback: BlogClickHandler) :
    UpdatableAdapter<BlogsAdapter.ViewHolder, Blog>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.blogImage
        val title: TextView = view.blogTitle
        val preview: TextView = view.blogPreview
        val category: TextView = view.blogCategory

        init {
            itemView.setOnClickListener {
                mCallback.onBlogClicked(items[bindingAdapterPosition])
            }
        }
    }

    override fun createAdapterViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = items[position]
        holder.apply {
            title.text = blog.title
            preview.text = blog.body
            category.text = "category: ${blog.category}"

            Glide.with(image.context)
                .load(blog.image)
                .centerCrop()
                .into(image)
        }
    }

    override fun getItemCount() = items.size
}

interface BlogClickHandler {
    fun onBlogClicked(blog: Blog)
}