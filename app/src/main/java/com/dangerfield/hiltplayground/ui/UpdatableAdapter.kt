package com.dangerfield.hiltplayground.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class UpdatableAdapter<T : RecyclerView.ViewHolder?, R> : RecyclerView.Adapter<T>() {

    var items: List<R> = listOf()
        set(value) {
            field = processItemsChange(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return createAdapterViewHolder(parent, viewType)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: T, position: Int) {
    }

    abstract fun createAdapterViewHolder(parent: ViewGroup, viewType: Int): T

    open fun processItemsChange(value: List<R>): List<R> {
        return value
    }
}