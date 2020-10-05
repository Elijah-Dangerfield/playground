package com.dangerfield.hiltplayground.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarouselView @JvmOverloads constructor(context: Context,
                                             attrs: AttributeSet? = null,
                                             defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    sealed class CarouselTileSize {
        object Small : CarouselTileSize()
        object Medium : CarouselTileSize()
        object Large : CarouselTileSize()
        object Full : CarouselTileSize()

        data class Fixed(@DimenRes val widthResId: Int) : CarouselTileSize()
    }


    /**
     * CarouselTileSize must be set in order for this view to work properly. We calculate the
     * itemWidth per item in the carousel, the total span and peek percentage based on what
     * tileSize we are dealing with. See CarouselViewExtensions to see these calculations.
     */
    lateinit var tileSize: CarouselTileSize

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

}

sealed class CarouselParams(@DimenRes open val gutter: Int,
                            @DimenRes open val leftRightPadding: Int)  {

    data class PeekPercentage(@IntegerRes val span: Int,
                              @DimenRes val peekPercentage: Int,
                              @DimenRes override val gutter: Int,
                              @DimenRes override val leftRightPadding: Int) : CarouselParams(gutter = gutter,
        leftRightPadding = leftRightPadding)

    data class FixedWidth(@DimenRes val width: Int,
                          @DimenRes override val gutter: Int,
                          @DimenRes override val leftRightPadding: Int) : CarouselParams(gutter = gutter,
        leftRightPadding = leftRightPadding)
}