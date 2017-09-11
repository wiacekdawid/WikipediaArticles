package com.wiacek.wikipediaarticles.data.db

import com.wiacek.wikipediaarticles.data.db.model.Article
import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by wiacek.dawid@gmail.com
 */

object ArticleDbHelper {
    fun add(realm: Realm, articles: List<Article>): Completable = Completable.fromAction {
        realm.executeTransaction {
            it.deleteAll()
            it.insertOrUpdate(articles) }
    }

    fun getAllItems(realm: Realm): RealmResults<Article> = realm
            .where(Article::class.java)
            .findAll()
}