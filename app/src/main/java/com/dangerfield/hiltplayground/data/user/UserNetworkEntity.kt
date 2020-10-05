package com.dangerfield.hiltplayground.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserNetworkEntity(
    @SerializedName("address")
    @Expose
    val address: Address = Address.DEFAULT,

    @SerializedName("company")
    @Expose
    val company: Company = Company.DEFAULT,

    @SerializedName("email")
    @Expose
    val email: String = "",

    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("name")
    @Expose
    val name: String = "",

    @SerializedName("phone")
    @Expose
    val phone: String = "",

    @SerializedName("username")
    @Expose
    val username: String = "",

    @SerializedName("website")
    @Expose
    val website: String = ""
)