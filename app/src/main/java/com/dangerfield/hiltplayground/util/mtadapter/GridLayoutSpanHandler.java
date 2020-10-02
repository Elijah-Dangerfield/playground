package com.dangerfield.hiltplayground.util.mtadapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dangerfield.hiltplayground.util.mtadapter.interfaces.SpanHandler;

/**
 * Default implementation of SpanHandler that uses the TypeAdapters
 * getSpan() method to calculate spans for each item.
 *
 * This is only utilized for GridLayoutManagers.
 */
public class GridLayoutSpanHandler implements SpanHandler {

    private final MultiTypeAdapter mAdapter;
    private boolean mSpanLookupSet;

    GridLayoutSpanHandler(@NonNull final MultiTypeAdapter adapter){
        mAdapter = adapter;
        mSpanLookupSet = false;
    }

    @Override
    public void calculateSpan(@NonNull final RecyclerView.LayoutManager layoutManager) {
        if (!mSpanLookupSet && layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mAdapter.getTypeAdapterForPosition(position).getSpan();
                }
            });
            mSpanLookupSet = true;
        }
    }
}
