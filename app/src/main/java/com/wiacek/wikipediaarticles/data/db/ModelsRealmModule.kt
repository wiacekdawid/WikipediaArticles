package com.wiacek.wikipediaarticles.data.db

import com.wiacek.wikipediaarticles.data.db.model.Article
import io.realm.annotations.RealmModule
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */
@RealmModule(classes = arrayOf(Article::class))
class ModelsRealmModule @Inject
internal constructor()