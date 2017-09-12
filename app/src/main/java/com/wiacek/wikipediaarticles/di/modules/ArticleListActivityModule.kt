package com.wiacek.wikipediaarticles.di.modules

import com.wiacek.wikipediaarticles.api.WikipediaService
import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.data.DataManagerImp
import com.wiacek.wikipediaarticles.data.remote.RemoteDataSource
import com.wiacek.wikipediaarticles.data.remote.RemoteDataSourceImp
import com.wiacek.wikipediaarticles.di.scopes.ActivityScope
import com.wiacek.wikipediaarticles.ui.activity.ArticleListActivity
import com.wiacek.wikipediaarticles.ui.activity.AttachedArticleListActivity
import com.wiacek.wikipediaarticles.ui.activity.AttachedArticleListActivityImp
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class ArticleListActivityModule(private val articleListActivity: ArticleListActivity) {
    @ActivityScope
    @Provides
    internal fun provideAttachedArticleListActivity(): AttachedArticleListActivity {
        return AttachedArticleListActivityImp(WeakReference<ArticleListActivity>(articleListActivity))
    }

    @ActivityScope
    @Provides
    internal fun provideRemoteDataSource(wikipediaService: WikipediaService): RemoteDataSource {
        return RemoteDataSourceImp(wikipediaService)
    }

    @ActivityScope
    @Provides
    internal fun provideDataManager(remoteDataSource: RemoteDataSource): DataManager {
        return DataManagerImp(remoteDataSource)
    }
}
