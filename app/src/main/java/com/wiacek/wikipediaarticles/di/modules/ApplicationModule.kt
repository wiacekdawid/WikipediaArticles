package com.wiacek.wikipediaarticles.di.modules

import android.content.Context
import android.location.LocationManager
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

    @Provides
    @ApplicationScope
    fun provideLocationManager(@Named("ApplicationContext") context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}