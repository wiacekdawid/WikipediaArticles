package com.wiacek.wikipediaarticles.bindings

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by wiacek.dawid@gmail.com
 */

@BindingAdapter("visibility")
fun setViewVisibilityByString(view: View,
                              text: String?) {
    view.visibility = if (text == null || text.isEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("android:visibility")
fun setViewVisibilityByBoolean(view: View,
                               value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}