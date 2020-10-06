package com.dangerfield.hiltplayground.ui.home

import androidx.recyclerview.widget.RecyclerView

abstract class IhrAdapter< VH : RecyclerView.ViewHolder?, in T>: RecyclerView.Adapter<VH>()
{
    abstract fun setItems(data: List<T>)

    abstract fun isMyData(data: List<Any>) : Boolean

}