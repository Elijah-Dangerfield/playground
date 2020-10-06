package com.dangerfield.hiltplayground.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.ui.views.CarouselView
import com.dangerfield.hiltplayground.util.IBundleState
import com.dangerfield.hiltplayground.util.inflate

class CarouselViewHolderV2(parent: ViewGroup, adapters: RecyclerView.Adapter<out RecyclerView.ViewHolder>
) : RecyclerView.ViewHolder(parent.inflate(R.layout.layout_carousel_view)), IBundleState {

    private val carouselView: CarouselView = itemView.findViewById(R.id.carousel_view)

    private val setData: (List<Any>) -> Unit

    init {
        setData = initCarousel(carouselView, adapters)
    }

    fun bindData(data: List<Any>) {
        // use manager to set update adapters aka call setData()
    }


    private fun initCarousel(recyclerView: CarouselView, adapters: RecyclerView.Adapter<out RecyclerView.ViewHolder>): (List<Any>) -> Unit {

        val adapter = ConcatAdapter(adapters) // user, ad, etc
        recyclerView.adapter = adapter

        //this is where the manager would come in handy, the manager would say set items = manager.setItems()
        return { items -> adapter }
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