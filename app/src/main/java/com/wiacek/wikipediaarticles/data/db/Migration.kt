package com.wiacek.wikipediaarticles.data.db

import io.realm.DynamicRealm
import io.realm.RealmMigration
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class Migration @Inject
constructor() : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {}
}