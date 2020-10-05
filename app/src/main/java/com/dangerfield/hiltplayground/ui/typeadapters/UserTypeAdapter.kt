package com.dangerfield.hiltplayground.ui.typeadapters

import android.view.ViewGroup
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.ui.viewholders.UserViewHolder
import com.dangerfield.hiltplayground.util.mtadapter.TypeAdapter


class UserTypeAdapter : TypeAdapter<UserData, UserViewHolder>() {

    //TODO what are we using isMyData for? Do we need this?
    override fun isMyData(data: Any): Boolean {
        return data is UserData
    }

    //TODO can the super handle the id comparison?
    override fun isDataEqual(data1: UserData, data2: UserData): Boolean {
        return data1.id == data2.id
    }

    //does the diff need to make a change to the item? Have the contents changed?
    override fun areContentsSame(data1: UserData, data2: UserData): Boolean {
        return data1.name == data2.name
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup): UserViewHolder {
        return UserViewHolder.create(
            viewGroup
        )
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, data: UserData) {
        viewHolder.bind(data)
    }
}