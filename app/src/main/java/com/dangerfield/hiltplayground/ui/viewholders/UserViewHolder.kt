package com.dangerfield.hiltplayground.ui.viewholders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.util.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: UserData) {
       itemView.userName.text = data.name
    }

    companion object {
        @JvmStatic
        fun create(view: ViewGroup): UserViewHolder {
            return UserViewHolder(
                view.inflate(R.layout.item_user)
            )
        }
    }
}