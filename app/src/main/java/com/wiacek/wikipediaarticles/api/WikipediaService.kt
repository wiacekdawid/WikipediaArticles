package com.wiacek.wikipediaarticles.api

import com.wiacek.wikipediaarticles.api.objects.ArticleListResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wiacek.dawid@gmail.com
 */

interface WikipediaService {
    @GET("/w/api.php?action=query&list=geosearch&gsradius=10000&format=jsond")
    fun getArticleList(@Query("gscoord") latLon: String): Single<Response<ArticleListResponseDto>>
}