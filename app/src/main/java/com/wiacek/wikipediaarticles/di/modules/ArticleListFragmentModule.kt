package com.wiacek.wikipediaarticles.di.modules

import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.di.scopes.FragmentScope
import com.wiacek.wikipediaarticles.ui.list.*
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

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
    internal fun provideAttachedArticleListFragment(): AttachedArticleListFragment {
        return AttachedArticleListFragmentImp(WeakReference(articleListFragment))
    }

    @FragmentScope
    @Provides
    internal fun provideArticleListViewHandler(dataManager: DataManager,
                                        articleListViewModel: ArticleListViewModel,
                                               attachedArticleListFragment: AttachedArticleListFragment): ArticleListViewHandler {
        return ArticleListViewHandler(dataManager, articleListViewModel, attachedArticleListFragment)
    }
}
