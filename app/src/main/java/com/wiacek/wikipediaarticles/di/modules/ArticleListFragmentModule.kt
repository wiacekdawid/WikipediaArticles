package com.wiacek.wikipediaarticles.di.modules

import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.di.scopes.FragmentScope
import com.wiacek.wikipediaarticles.ui.list.ArticleListFragment
import com.wiacek.wikipediaarticles.ui.list.ArticleListViewHandler
import com.wiacek.wikipediaarticles.ui.list.ArticleListViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by wiacek.dawid@gmail.com
 */


@Module
class ArticleListFragmentModule(private val articleListFragment: ArticleListFragment) {
    @FragmentScope
    @Provides
    internal fun provideArticleListViewModel(): ArticleListViewModel {
        return ArticleListViewModel()
    }

    @FragmentScope
    @Provides
    internal fun provideArticleListViewHandler(dataManager: DataManager,
                                        articleListViewModel: ArticleListViewModel): ArticleListViewHandler {
        return ArticleListViewHandler(dataManager, articleListViewModel)
    }
}
