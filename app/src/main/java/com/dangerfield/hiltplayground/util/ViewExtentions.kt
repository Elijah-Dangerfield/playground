package com.dangerfield.hiltplayground.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.ui.home.CarouselAdapter
import com.dangerfield.hiltplayground.ui.home.IhrAdapter

fun View.goneUnless(condition: Boolean) {
    this.visibility = if(condition) View.VISIBLE else View.GONE
}


fun ViewGroup.inflate(@LayoutRes layout: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToParent)
}

