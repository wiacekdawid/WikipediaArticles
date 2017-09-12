package com.wiacek.wikipediaarticles.data.remote

import com.wiacek.wikipediaarticles.api.WikipediaService
import com.wiacek.wikipediaarticles.data.db.mappers.ArticleDtoToArticlerMapper
import com.wiacek.wikipediaarticles.data.db.model.Article
import io.reactivex.Single
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */

class RemoteDataSourceImp(val wikipediaService: WikipediaService): RemoteDataSource {
    override fun getArticleList(latLon: String): Single<List<Article>> {
        return wikipediaService.getArticleList(latLon)
                .doOnError { t -> Timber.e(t.message) }
                .map{response ->
                    val list: MutableList<Article> = mutableListOf()
                    if(response.isSuccessful) {
                        response.body()?.geoSearchDto?.articleList?.mapTo(list) {
                            ArticleDtoToArticlerMapper.transform(it)
                        }
                    }
                    list
                }
    }

}