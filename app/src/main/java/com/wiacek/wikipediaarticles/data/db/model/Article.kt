package com.wiacek.wikipediaarticles.data.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by wiacek.dawid@gmail.com
 */

@RealmClass
open class Article(
        @PrimaryKey
        var id: Int = 0,
        var title: String = "",
        var subtitle: String = "",
        var body: String = "",
        var date: Date = Date()) : RealmObject()