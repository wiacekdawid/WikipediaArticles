package com.wiacek.wikipediaarticles.di.components

import com.wiacek.wikipediaarticles.di.modules.ArticleListFragmentModule
import com.wiacek.wikipediaarticles.di.scopes.FragmentScope
import com.wiacek.wikipediaarticles.ui.list.ArticleListFragment
import dagger.Subcomponent

/**
 * Created by wiacek.dawid@gmail.com
 */

@FragmentScope
@Subcomponent(modules = arrayOf(ArticleListFragmentModule::class))
interface ArticleListFragmentComponent {
    fun inject(articleListFragment: ArticleListFragment)
}