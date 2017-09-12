package com.wiacek.wikipediaarticles.data.db.mappers

import com.wiacek.wikipediaarticles.api.objects.ArticleDto
import com.wiacek.wikipediaarticles.data.db.model.Article

/**
 * Created by wiacek.dawid@gmail.com
 */

object ArticleDtoToArticlerMapper {
    fun transform(articleDto: ArticleDto): Article {
        var article = Article(id = articleDto.pageid,
                title = articleDto.title,
                dist = articleDto.dist)
        return article
    }
}