package com.wiacek.pulselive.di.modules

import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.data.DataManagerImp
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
    internal fun provideDataManager(): DataManager {
        return DataManagerImp()
    }
}
