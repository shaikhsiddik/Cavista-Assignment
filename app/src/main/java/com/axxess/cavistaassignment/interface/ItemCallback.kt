package com.axxess.cavistaassignment.`interface`

import android.view.View

public interface ItemCallback<T, V : View> {
    fun OnClick(t: T, v: V);
}