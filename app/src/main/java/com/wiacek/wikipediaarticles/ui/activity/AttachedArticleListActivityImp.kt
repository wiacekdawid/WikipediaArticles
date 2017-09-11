package com.wiacek.wikipediaarticles.ui.activity

import java.lang.ref.WeakReference

/**
 * Created by wiacek.dawid@gmail.com
 */

class AttachedArticleListActivityImp(private val articleListActivity:
                                   WeakReference<ArticleListActivity>): AttachedArticleListActivity {
    override fun selectItem(id: String) {
        articleListActivity.get()?.onListItemSelected(id)
    }
}