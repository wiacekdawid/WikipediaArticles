package com.wiacek.wikipediaarticles.di.components

import com.wiacek.pulselive.di.components.ArticleListActivityComponent
import com.wiacek.pulselive.di.modules.ApplicationModule
import com.wiacek.pulselive.di.modules.ArticleListActivityModule
import com.wiacek.wikipediaarticles.WikipediaArticlesApplication
import com.wiacek.wikipediaarticles.di.modules.NetModule
import com.wiacek.wikipediaarticles.di.scopes.ApplicationScope
import dagger.Component

/**
 * Created by wiacek.dawid@gmail.com
 */

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))
interface ApplicationComponent {
    fun inject(wikipediaArticlesApplication: WikipediaArticlesApplication)
    fun add(articleListActivityModule: ArticleListActivityModule): ArticleListActivityComponent
}