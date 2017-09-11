package com.wiacek.wikipediaarticles.ui.list.item

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.wikipediaarticles.BR
import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.ui.activity.AttachedArticleListActivity

/**
 * Created by wiacek.dawid@gmail.com
 */
class ItemViewModel(id: String = "",
                    title: String = "",
                    dist: String = "",
                    val attachedArticleListActivity: AttachedArticleListActivity): BaseObservable() {
    @get:Bindable
    var id = id
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var title = title
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var dist = dist
        set(value) {
            field = value
            notifyPropertyChanged(BR.dist)
        }

    fun onClickItem() {
        attachedArticleListActivity.selectItem(id)
    }

    fun setItem(article: Article?) {
        id = article?.id ?: ""
        title = article?.title ?: ""
        dist = article?.dist ?: ""
        notifyChange()
    }
}