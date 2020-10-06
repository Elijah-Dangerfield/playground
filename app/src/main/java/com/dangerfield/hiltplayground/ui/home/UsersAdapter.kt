package com.dangerfield.hiltplayground.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.ui.viewholders.UserViewHolder

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var items = listOf<UserData>()

    fun setItems(newItems: List<UserData>) {
        items = newItems
        notifyDataSetChanged()
        //TODO use a diff
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }
}