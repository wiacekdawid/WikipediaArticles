package com.wiacek.wikipediaarticles.data

import com.wiacek.wikipediaarticles.data.db.model.Article
import io.reactivex.Single

/**
 * Created by wiacek.dawid@gmail.com
 */

interface DataManager {
    fun getArticleList(): Single<Article>
}