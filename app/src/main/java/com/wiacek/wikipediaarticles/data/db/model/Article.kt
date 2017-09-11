package com.wiacek.wikipediaarticles.data.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by wiacek.dawid@gmail.com
 */

@RealmClass
open class Article(
        @PrimaryKey
        var id: String = "",
        var title: String = "",
        var dist: String = "") : RealmObject()