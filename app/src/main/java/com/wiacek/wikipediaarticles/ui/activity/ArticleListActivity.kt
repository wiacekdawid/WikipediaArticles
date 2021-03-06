package com.wiacek.wikipediaarticles.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wiacek.wikipediaarticles.R
import com.wiacek.wikipediaarticles.WikipediaArticlesApplication
import com.wiacek.wikipediaarticles.di.components.ArticleListActivityComponent
import com.wiacek.wikipediaarticles.di.modules.ArticleListActivityModule
import com.wiacek.wikipediaarticles.ui.list.ArticleListFragment
import com.wiacek.wikipediaarticles.ui.list.OnListItemSelectedListener


/**
 * Created by wiacek.dawid@gmail.com
 */

class ArticleListActivity: AppCompatActivity(), OnListItemSelectedListener {

    var activityComponent: ArticleListActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArticleListActivityComponent()?.inject(this)
        setContentView(R.layout.activity_article_list)

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return
            }

            val articleListFragment = ArticleListFragment()
            articleListFragment.setArguments(intent.extras)

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, articleListFragment).commit()
        }
    }

    fun getArticleListActivityComponent(): ArticleListActivityComponent? {
        if (activityComponent == null) {
            activityComponent = (applicationContext as WikipediaArticlesApplication)
                    .applicationComponent
                    ?.add(ArticleListActivityModule(this))
        }
        return activityComponent
    }

    override fun onListItemSelected(id: String) {
        val webpage = Uri.parse("https://en.wikipedia.org/?curid=" + id)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}