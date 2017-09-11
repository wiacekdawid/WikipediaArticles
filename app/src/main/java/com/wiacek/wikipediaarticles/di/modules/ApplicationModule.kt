package com.wiacek.pulselive.di.modules

import android.content.Context
import com.wiacek.wikipediaarticles.WikipediaArticlesApplication
import com.wiacek.wikipediaarticles.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by wiacek.dawid@gmail.com
 */
@Module
class ApplicationModule(private val wikipediaArticlesApplication: WikipediaArticlesApplication) {

    @Provides
    @Named("ApplicationContext")
    @ApplicationScope
    internal fun provideApplicationContext(): Context {
        return wikipediaArticlesApplication.applicationContext
    }
}