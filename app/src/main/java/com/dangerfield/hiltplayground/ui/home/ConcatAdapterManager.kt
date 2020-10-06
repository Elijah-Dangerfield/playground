package com.dangerfield.hiltplayground.ui.home

import android.util.Log
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

/*
If we make an IhrAdapter that has a set function that would be ideal here as we could create the adapter
and add it to the list and for any one set the data.
 */
class ConcatAdapterManager(private val adapterMap: Map< Class<out Any>, Class<out IhrAdapter<out RecyclerView.ViewHolder>>>) {

    private var adapters = arrayListOf<IhrAdapter<out RecyclerView.ViewHolder>>()

    val concatAdapter = ConcatAdapter(adapters)

    fun setData(data: List<List<Any>>) {
        data.forEachIndexed { index, list ->
            val dataType = list.firstOrNull()?.javaClass
            dataType?.let { dt ->

                adapterMap[dt]?.let {adapterType ->
                    if(adapters.size > index && adapterType.isAssignableFrom(adapters[index]::class.java)) {
                        // Then the adapter is in place
                        // we just need to update the data
                        Log.d("Elijah", "Adapter was found. Setting data for ${adapterType.simpleName}")
                        adapters[index].setItems(list)
                        concatAdapter.notifyDataSetChanged()

                    } else {
                        // then we need to create the adapter at this place
                        Log.d("Elijah", "Adapter was NOT found. Adding ${adapterType.simpleName} at index: $index")
                        val newAdapter = adapterType.newInstance()
                        newAdapter.setItems(list)
                        adapters.add(index, newAdapter)
                        concatAdapter.notifyDataSetChanged()

                    }
                } ?: throw Exception("No Adapter Found for type : ${data::class.java.simpleName}")
            }
        }

        concatAdapter.notifyDataSetChanged()
    }
}