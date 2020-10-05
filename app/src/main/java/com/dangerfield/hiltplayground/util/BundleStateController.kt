package com.dangerfield.hiltplayground.util

import android.os.Bundle
import android.util.SparseArray

class BundleStateController {
    private val mSavedStateBundles = SparseArray<Bundle>()
    fun saveStateFor(itemPos: Int, bundleState: IBundleState) {
        createBundleForViewHolderIfNoneExist(itemPos)
        bundleState.onSaveState(getStateForPosition(itemPos))
    }

    fun loadStateFor(itemPos: Int, bundleState: IBundleState) {
        createBundleForViewHolderIfNoneExist(itemPos)
        bundleState.onLoadState(getStateForPosition(itemPos))
    }

    private fun createBundleForViewHolderIfNoneExist(position: Int) {
        if (mSavedStateBundles[position] == null) {
            mSavedStateBundles.put(position, Bundle())
        }
    }

    private fun getStateForPosition(position: Int): Bundle {
        return mSavedStateBundles[position]
    }
}