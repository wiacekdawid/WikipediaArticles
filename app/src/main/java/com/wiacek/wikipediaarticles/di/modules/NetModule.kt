package com.wiacek.wikipediaarticles.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wiacek.wikipediaarticles.BuildConfig
import com.wiacek.wikipediaarticles.api.WikipediaService
import com.wiacek.wikipediaarticles.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class NetModule {

    private val TIMEOUT = 15L

    @Provides
    @ApplicationScope
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @ApplicationScope
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun providePulseliveService(retrofit: Retrofit): WikipediaService {
        return retrofit.create<WikipediaService>(WikipediaService::class.java)
    }
}