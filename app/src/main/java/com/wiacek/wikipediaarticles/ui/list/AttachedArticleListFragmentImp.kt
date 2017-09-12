package com.wiacek.wikipediaarticles.ui.list

import android.location.Location
import java.lang.ref.WeakReference

/**
 * Created by wiacek.dawid@gmail.com
 */

class AttachedArticleListFragmentImp(private val articleListFragment:
                                     WeakReference<ArticleListFragment>): AttachedArticleListFragment {
    override fun getLocation(): Location? {
        return articleListFragment.get()?.getLocation()
    }
}