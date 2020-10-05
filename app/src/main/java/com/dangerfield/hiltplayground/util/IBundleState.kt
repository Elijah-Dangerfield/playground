package com.dangerfield.hiltplayground.util

import android.os.Bundle

interface IBundleState {
    fun onLoadState(savedState: Bundle)
    fun onSaveState(savedState: Bundle)
}
