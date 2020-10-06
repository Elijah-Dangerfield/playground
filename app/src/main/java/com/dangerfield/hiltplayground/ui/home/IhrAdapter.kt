package com.dangerfield.hiltplayground.ui.home

import androidx.recyclerview.widget.RecyclerView

abstract class IhrAdapter< VH : RecyclerView.ViewHolder?>: RecyclerView.Adapter<VH>() {

    abstract fun setItems(data: List<Any>)

}