package com.dangerfield.hiltplayground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val mCallback: UserClickHandler) :
    UpdatableAdapter<UsersAdapter.ViewHolder, String>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userName : TextView = view.userName

        init {
            itemView.setOnClickListener {
                mCallback.onUserClicked(items[bindingAdapterPosition])
            }
        }
    }

    override fun createAdapterViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.userName.text = user
    }

    override fun getItemCount() = items.size
}

interface UserClickHandler {
    fun onUserClicked(user: String)
}