package com.dangerfield.hiltplayground.ui.viewholders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.util.inflate
import kotlinx.android.synthetic.main.layout_simple_header.view.*

//TODO maybe have TypeAdapter take in an interface that extends View holder and has a create and bind function
class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: HeaderData) {
        itemView.headerTitle.text = data.title
    }

    companion object {
        @JvmStatic
        fun create(view: ViewGroup): HeaderViewHolder {
            return HeaderViewHolder(
                view.inflate(R.layout.layout_simple_header)
            )
        }
    }
}