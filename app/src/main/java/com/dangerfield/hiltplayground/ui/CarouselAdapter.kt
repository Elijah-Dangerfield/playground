package com.dangerfield.hiltplayground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import kotlinx.android.synthetic.main.item_carousel_header.view.*
import kotlinx.android.synthetic.main.item_carousel_recycler.view.*

enum class CarouselViewType(val value: Int) {
    Header(0), Adapter(1)
}

sealed class CarouselAdapter<T>(
    header: String,
    private val adapter: UpdatableAdapter<out RecyclerView.ViewHolder, T>
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    sealed class CarouselItem(val itemType: Int) {
        class Header(val text: String) : CarouselItem(CarouselViewType.Header.value)
        class AdapterWrapper(val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder?>) :
            CarouselItem(CarouselViewType.Adapter.value)
    }

    private var carouselItems = listOf(
        CarouselItem.Header(header),
        CarouselItem.AdapterWrapper(adapter)
    )

    fun update(list: List<T>) {
        adapter.items = list
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        class HeaderViewHolder(view: View) : ViewHolder(view) {
            val header: TextView = view.tv_carousel_header
        }

        class ListViewHolder(view: View) : ViewHolder(view) {
            val rv: RecyclerView = view.rv
        }
    }

    override fun getItemViewType(position: Int): Int {
        return carouselItems[position].itemType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            CarouselViewType.Header.value -> {
                ViewHolder.HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_carousel_header,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ViewHolder.ListViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_carousel_recycler,
                        parent,
                        false
                    )
                ).also { this.applyLayout(it.rv) }
            }
        }
    }

    override fun getItemCount() = carouselItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = carouselItems[position]) {
            is CarouselItem.Header -> {
                (holder as ViewHolder.HeaderViewHolder).header.text = item.text
            }
            is CarouselItem.AdapterWrapper -> {
                val carouselRv = (holder as ViewHolder.ListViewHolder).rv
                carouselRv.adapter = item.adapter
            }
        }
    }

    abstract fun applyLayout(it: RecyclerView)

    class Vertical<T>(
        header: String,
        adapter: UpdatableAdapter<out RecyclerView.ViewHolder, T>
    ) : CarouselAdapter<T>(header, adapter) {
        override fun applyLayout(it: RecyclerView) {
            it.layoutManager = LinearLayoutManager(it.context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

    }

    class Horizontal<T>(
        header: String,
        adapter: UpdatableAdapter<out RecyclerView.ViewHolder, T>
    ) : CarouselAdapter<T>(header, adapter) {
        override fun applyLayout(it: RecyclerView) {
            it.layoutManager = LinearLayoutManager(it.context).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
    }
}