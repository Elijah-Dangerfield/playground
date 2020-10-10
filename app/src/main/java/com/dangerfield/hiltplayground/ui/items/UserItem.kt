package com.dangerfield.hiltplayground.ui.items

import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.UserData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_user.*

class UserItem(private val userData: UserData) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.userName.text = userData.name
    }

    override fun getLayout() = R.layout.item_user
}