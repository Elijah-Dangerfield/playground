package com.dangerfield.hiltplayground.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.CarouselData
import com.dangerfield.hiltplayground.ui.views.CarouselView
import com.dangerfield.hiltplayground.util.IBundleState
import com.dangerfield.hiltplayground.util.inflate

class CarouselViewHolderV2(parent: ViewGroup, private val adapter : IhrAdapter<out RecyclerView.ViewHolder, Any>
) : RecyclerView.ViewHolder(parent.inflate(R.layout.layout_carousel_view)), IBundleState {

    private val carouselView: CarouselView = itemView.findViewById(R.id.carousel_view)

    init {
        carouselView.adapter = adapter
    }

    fun bindData(data: CarouselData) {
        adapter.setItems(data.items)
    }

    override fun onLoadState(savedState: Bundle) {
        carouselView.post { carouselView.scrollBy(savedState.getInt(POSITION_STATE_KEY), 0) }
    }

    override fun onSaveState(savedState: Bundle) {
        savedState.putInt(POSITION_STATE_KEY, carouselView.computeHorizontalScrollOffset())
    }

    companion object {
        private const val POSITION_STATE_KEY = "POSITION_STATE_KEY"
    }
}