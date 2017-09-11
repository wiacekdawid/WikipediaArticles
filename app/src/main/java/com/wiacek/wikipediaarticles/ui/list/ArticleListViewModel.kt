package com.wiacek.wikipediaarticles.ui.list

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.wikipediaarticles.BR
import org.parceler.Parcel

/**
 * Created by wiacek.dawid@gmail.com
 */

@Parcel(Parcel.Serialization.BEAN)
class ArticleListViewModel(loading: Boolean = false,
                    reconnectMessageVisible: Boolean = false): BaseObservable() {
    @get:Bindable
    var loading = loading
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    @get:Bindable
    var reconnectMessageVisible = reconnectMessageVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.reconnectMessageVisible)
        }
}