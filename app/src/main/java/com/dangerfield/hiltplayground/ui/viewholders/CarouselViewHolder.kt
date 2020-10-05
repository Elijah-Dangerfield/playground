package com.dangerfield.hiltplayground.ui.viewholders

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.ui.views.CarouselView
import com.dangerfield.hiltplayground.util.IBundleState
import com.dangerfield.hiltplayground.util.inflate
import com.dangerfield.hiltplayground.util.mtadapter.Items
import com.dangerfield.hiltplayground.util.mtadapter.MultiTypeAdapter
import com.dangerfield.hiltplayground.util.mtadapter.TypeAdapter


class CarouselViewHolder(parent: ViewGroup,
                         binders: List<TypeAdapter<*, *>>,
                         recyclerViewTag: String? = null,
                         tileSize: CarouselView.CarouselTileSize = CarouselView.CarouselTileSize.Medium
)

    : RecyclerView.ViewHolder(parent.inflate(R.layout.layout_carousel_view)),
    IBundleState {

    private val carouselView: CarouselView = itemView.findViewById(R.id.carousel_view)

    private val setData: (Items) -> Unit

    init {
        carouselView.tileSize = tileSize
        recyclerViewTag?.let { carouselView.tag = it }
        setData = initCarousel(carouselView, binders)
    }

    fun bindData(data: Items) {
        setData(data)
    }


    private fun initCarousel(recyclerView: CarouselView,
                             typeAdapters: List<TypeAdapter<*, *>>): (Items) -> Unit {

       // recyclerView.addCarouselDecoration(recyclerView.params.gutter)

       // val itemWidth = carouselView.itemWidth

        val adapter = MultiTypeAdapter(typeAdapters)

        recyclerView.adapter = adapter

        return { items -> adapter.setData(items) }
    }

//    private fun <D, V : RecyclerView.ViewHolder> wrapToSetTileWidth(original: TypeAdapter<D, V>,
//                                                                    itemWidth: Int): TypeAdapter<D, V> {
//        return HeterogeneousBinderWrappers.afterBinding(original) { viewHolder ->
//            viewHolder.itemView.layoutParams = RecyclerView.LayoutParams(itemWidth,
//                RecyclerView.LayoutParams.WRAP_CONTENT)
//        }
//    }


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