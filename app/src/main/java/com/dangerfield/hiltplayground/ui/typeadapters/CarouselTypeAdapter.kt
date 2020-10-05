package com.dangerfield.hiltplayground.ui.typeadapters

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.models.CarouselData
import com.dangerfield.hiltplayground.models.CarouselId
import com.dangerfield.hiltplayground.ui.views.CarouselView
import com.dangerfield.hiltplayground.ui.viewholders.CarouselViewHolder
import com.dangerfield.hiltplayground.util.BundleStateController
import com.dangerfield.hiltplayground.util.mtadapter.TypeAdapter


/**
 * Multiple TypeAdapters within the SAME Carousel can be passed into this CarouselTypeAdapter and it will support different
 * ViewHolder within the SAME Carousel.
 *
 * If you have Multiple Carousels on the same screen within the same view, each on screen carousel should have it's own CarouselTypeAdapter.
 * DO NOT add a List of 'typeAdapters' that belong to separate Carousels to the same CarouselTypeAdapter. We need to TAG each Carousel's
 * RecyclerView with a specific tag for testing so we cannot use just 1 CarouselTypeAdapter for multiple Carousels on the same screen.
 *
 */
class CarouselTypeAdapter @JvmOverloads constructor(
    private val typeAdapters: List<TypeAdapter<*, *>>,
    private val tileSize: CarouselView.CarouselTileSize = CarouselView.CarouselTileSize.Medium,
    private val tag: String? = null,
    private val isMyData: ((CarouselData) -> Boolean)? = null) : TypeAdapter<CarouselData, CarouselViewHolder>() {

    constructor(typeAdapter: TypeAdapter<*, *>,
                tileSize: CarouselView.CarouselTileSize = CarouselView.CarouselTileSize.Medium,
                tag: String? = null,
                isMyData: ((CarouselData) -> Boolean)? = null) : this(listOf(typeAdapter), tileSize, tag, isMyData)

    private val bundleStateController = BundleStateController()

    override fun isMyData(item: Any): Boolean {
        if (item !is CarouselData) return false
        if (isMyData != null) return isMyData.invoke(item)
        val internalData = item.data.data().firstOrNull()
        return if (internalData == null) {
            typeAdapters.isNotEmpty()
        } else {
            typeAdapters.any { it.isMyData(internalData) }
        }
    }

    override fun onCreateViewHolder(inflating: ViewGroup) =
        CarouselViewHolder(
            inflating,
            typeAdapters,
            tag,
            tileSize
        )

    override fun onBindViewHolder(viewHolder: CarouselViewHolder, data: CarouselData) {
        viewHolder.bindData(data.data)
    }

    override fun onAttach(view: CarouselViewHolder) {
        bundleStateController.loadStateFor(view.adapterPosition, view)
    }

    override fun onDetach(view: CarouselViewHolder) {
        bundleStateController.saveStateFor(view.adapterPosition, view)
    }

    override fun isDataEqual(data1: CarouselData, data2: CarouselData): Boolean = true

    override fun areContentsSame(data1: CarouselData, data2: CarouselData): Boolean = false

    override fun getChangePayload(oldData: CarouselData, newData: CarouselData, diffBundle: Bundle): Any = newData


}

//TODO: LOOKS LIKE WE ARENT EVEN USING THE TAG? MAYBE REMOVE IT AND USE ID FOR RECYCLER DIFF
fun <T, D : RecyclerView.ViewHolder> TypeAdapter<T, D>.toCarousel(tileSize: CarouselView.CarouselTileSize,
                                                                  tag: String,
                                                                  carouselId: CarouselId
) = CarouselTypeAdapter(
    typeAdapter = this,
    tag = tag,
    tileSize = tileSize,
    isMyData = { carouselId == it.carouselId })

fun <T, D : RecyclerView.ViewHolder> TypeAdapter<T, D>.toCarousel(tileSize: CarouselView.CarouselTileSize,
                                                                  tag: String,
                                                                  instanceChecker: ((CarouselData) -> Boolean)? = null) =
    CarouselTypeAdapter(
        typeAdapter = this,
        tag = tag,
        tileSize = tileSize,
        isMyData = instanceChecker
    )

fun List<TypeAdapter<*, *>>.toCarousel(tileSize: CarouselView.CarouselTileSize = CarouselView.CarouselTileSize.Medium,
                                       tag: String,
                                       instanceChecker: ((CarouselData) -> Boolean)? = null) =
    CarouselTypeAdapter(
        typeAdapters = this,
        tag = tag,
        tileSize = tileSize,
        isMyData = instanceChecker
    )
