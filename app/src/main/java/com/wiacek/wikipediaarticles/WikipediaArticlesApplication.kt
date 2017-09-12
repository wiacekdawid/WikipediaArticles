package com.wiacek.wikipediaarticles

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import com.wiacek.wikipediaarticles.di.modules.ApplicationModule
import com.wiacek.wikipediaarticles.data.db.DbConstants
import com.wiacek.wikipediaarticles.data.db.Migration
import com.wiacek.wikipediaarticles.data.db.ModelsRealmModule
import com.wiacek.wikipediaarticles.di.components.ApplicationComponent
import com.wiacek.wikipediaarticles.di.components.DaggerApplicationComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WikipediaArticlesApplication: MultiDexApplication() {

    @Inject
    lateinit var migration: Migration
    @Inject
    lateinit var modelsRealmModule: ModelsRealmModule

    var applicationComponent: ApplicationComponent? = null

    companion object {
        fun get(context: Context) : WikipediaArticlesApplication {
            return context.applicationContext as WikipediaArticlesApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        initializeDI()
        initializeDb()
        initializeStetho()
        initializeLeakCanary()
    }

    private fun initializeDI() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        applicationComponent?.inject(this)
    }

    private fun initializeLeakCanary() {
        if(!LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    private fun initializeDb() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .migration(migration)
                .modules(modelsRealmModule)
                .schemaVersion(DbConstants.REALM_SCHEMA_VERSION)
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun initializeStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                    .build())
                            .build())
        }
    }
}