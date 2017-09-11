package com.wiacek.pulselive.di.components

import com.wiacek.pulselive.di.modules.ArticleListActivityModule
import com.wiacek.wikipediaarticles.di.components.ArticleListFragmentComponent
import com.wiacek.wikipediaarticles.di.modules.ArticleListFragmentModule
import com.wiacek.wikipediaarticles.di.scopes.ActivityScope
import com.wiacek.wikipediaarticles.ui.activity.ArticleListActivity
import com.wiacek.wikipediaarticles.ui.list.ArticleListFragment
import dagger.Subcomponent

/**
 * Created by wiacek.dawid@gmail.com
 */

@ActivityScope
@Subcomponent(modules = arrayOf(ArticleListActivityModule::class))
interface ArticleListActivityComponent {
    fun inject(articleListActivity: ArticleListActivity)
    fun add(articleListFragmentModule: ArticleListFragmentModule): ArticleListFragmentComponent
}