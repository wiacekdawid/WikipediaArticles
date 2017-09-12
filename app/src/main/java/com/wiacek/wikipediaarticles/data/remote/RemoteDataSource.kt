package com.wiacek.wikipediaarticles.data.remote

import com.wiacek.wikipediaarticles.data.db.model.Article
import io.reactivex.Single

/**
 * Created by wiacek.dawid@gmail.com
 */

interface RemoteDataSource {
    fun getArticleList(lonLat: String): Single<List<Article>>
}