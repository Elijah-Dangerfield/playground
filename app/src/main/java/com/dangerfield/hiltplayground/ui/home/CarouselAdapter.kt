package com.dangerfield.hiltplayground.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.models.CarouselData

class CarouselAdapter(private val adapter: IhrAdapter<out RecyclerView.ViewHolder, Any>) : IhrAdapter<CarouselViewHolderV2, CarouselData>() {

    private var  items = listOf<CarouselData>()

    override fun setItems(data: List<CarouselData>) {
        items = data
        notifyDataSetChanged()
    }

    override fun isMyData(data: List<Any>): Boolean {
        return data.firstOrNull() is CarouselData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolderV2 {
        return CarouselViewHolderV2(parent, adapter)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolderV2, position: Int) {
        holder.bindData(items[position])
    }
}