package com.dangerfield.hiltplayground.models

import java.util.*

//TODO maybe have type adapter take in an abstract class where ID is defined in use for
// isDataTheSame. Looks like Groupie did this or move the function to the data
data class HeaderData(val title: String, val id: String = UUID.randomUUID().toString()) {
}