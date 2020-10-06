package com.dangerfield.hiltplayground.ui.home

import android.view.ViewGroup
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.ui.viewholders.UserViewHolder

class UsersAdapter : IhrAdapter<UserViewHolder, UserData>() {

    private var items = listOf<UserData>()

    override fun setItems(data: List<UserData>) {
        items = data
        notifyDataSetChanged()
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

    override fun isMyData(data: List<Any>): Boolean {
        return data.firstOrNull() is UserData
    }
}