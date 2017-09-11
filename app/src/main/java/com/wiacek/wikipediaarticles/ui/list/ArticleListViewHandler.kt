package com.wiacek.wikipediaarticles.ui.list

import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.data.db.ArticleDbHelper
import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.ui.base.ViewHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by wiacek.dawid@gmail.com
 */
class ArticleListViewHandler(val dataManager: DataManager,
                             var articleListViewModel: ArticleListViewModel): ViewHandler {
    private val compositeDisposable = CompositeDisposable()
    private var realm: Realm? = null

    override fun onDetach() {
        compositeDisposable.clear()
        realm?.close()
    }

    override fun onAttach() {
        articleListViewModel.loading = false
        realm = Realm.getDefaultInstance()
        onRefresh()
    }

    fun onRefresh() {
        if(!articleListViewModel.loading) {
            articleListViewModel.loading = true
            var disposable = dataManager.getArticleList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ value -> onSuccess() },
                            { t -> onError() })
            compositeDisposable.add(disposable)
        }
    }

    fun getAdapterDataFromDb(): RealmResults<Article>? {
        val results = ArticleDbHelper.getAllItems(realm ?: Realm.getDefaultInstance())
        results.addChangeListener { items, changeSet -> onListUpdate(items) }
        onListUpdate(results)
        return results
    }

    private fun onListUpdate(items: RealmResults<Article>) {
        articleListViewModel.reconnectMessageVisible = items.size == 0
    }

    fun onSuccess() {
        articleListViewModel.loading = false
    }

    fun onError() {
        articleListViewModel.loading = false
    }
}