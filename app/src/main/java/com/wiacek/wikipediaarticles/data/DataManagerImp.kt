package com.wiacek.wikipediaarticles.data

import com.wiacek.wikipediaarticles.data.db.ArticleDbHelper
import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.data.remote.RemoteDataSource
import io.reactivex.Single
import io.realm.Realm
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */

class DataManagerImp(val remoteDataSource: RemoteDataSource): DataManager {
    override fun getArticleList(lonLat: String): Single<List<Article>> {
        return remoteDataSource.getArticleList(lonLat)
                .doOnSuccess { list ->
                    val realm = Realm.getDefaultInstance()
                    try{
                        ArticleDbHelper.add(realm, list)
                                .subscribe({}, {t -> Timber.e(t.message)})
                    }
                    finally {
                        realm.close()
                    }
                }
    }

}