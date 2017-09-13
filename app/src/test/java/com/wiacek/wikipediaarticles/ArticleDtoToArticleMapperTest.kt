package com.wiacek.wikipediaarticles

import com.wiacek.wikipediaarticles.api.objects.ArticleDto
import com.wiacek.wikipediaarticles.data.db.mappers.ArticleDtoToArticlerMapper
import org.junit.Test

/**
 * Created by wiacek.dawid@gmail.com
 */

class ArticleDtoToArticleMapperTest {
    @Test
    fun correctTransformArticleDtoToArticle() {
        //given
        val articleDto = ArticleDto(pageid = "pageid",
                title = "title",
                dist = "dist")


        //when
        val article = ArticleDtoToArticlerMapper.transform(articleDto)

        //expect
        with(article) {
            org.junit.Assert.assertEquals(id, article.id)
            org.junit.Assert.assertEquals(title, article.title)
            org.junit.Assert.assertEquals(dist, article.dist)
        }
    }
}
