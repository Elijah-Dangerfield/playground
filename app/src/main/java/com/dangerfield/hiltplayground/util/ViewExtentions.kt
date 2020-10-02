package com.dangerfield.hiltplayground.util

import android.view.View


fun View.goneUnless(condition: Boolean) {
    this.visibility = if(condition) View.VISIBLE else View.GONE
}