package com.dangerfield.hiltplayground.ui.items

import com.bumptech.glide.Glide
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.BlogData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_blog.*

class BlogItem(private val blogData: BlogData) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
                blogTitle.text = blogData.title
                blogPreview.text = blogData.body
                blogCategory.text = "category: ${blogData.category}"

                Glide.with(blogImage.context)
                    .load(blogData.image)
                    .centerCrop()
                    .into(blogImage)
            }
    }

    override fun getLayout() = R.layout.item_blog

}