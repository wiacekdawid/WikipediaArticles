package com.wiacek.wikipediaarticles.data

import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.data.remote.RemoteDataSource
import io.reactivex.Single

/**
 * Created by wiacek.dawid@gmail.com
 */

class DataManagerImp(val remoteDataSource: RemoteDataSource): DataManager {
    override fun getArticleList(lonLat: String): Single<List<Article>> {
        return remoteDataSource.getArticleList(lonLat)
    }

}