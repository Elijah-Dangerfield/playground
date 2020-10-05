package com.dangerfield.hiltplayground.data.user

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
) {
    companion object {
        val DEFAULT = Company("", "", "")
    }
}