package com.wiacek.wikipediaarticles

import android.location.Location
import com.wiacek.wikipediaarticles.data.DataManager
import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.ui.list.ArticleListViewHandler
import com.wiacek.wikipediaarticles.ui.list.ArticleListViewModel
import com.wiacek.wikipediaarticles.ui.list.AttachedArticleListFragment
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by wiacek.dawid@gmail.com
 */

class ArticleListViewHandlerTest {

    @Rule
    @JvmField
    val trampolineSchedulerRule = RxJavaSchedulersRule()

    var articleListViewHandler: ArticleListViewHandler? = null

    @Mock
    private lateinit var articleListViewModel: ArticleListViewModel

    @Mock
    private lateinit var dataManager: DataManager

    @Mock
    private lateinit var attachedArticleListFragment: AttachedArticleListFragment

    @Mock
    private lateinit var location: Location

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        articleListViewHandler = ArticleListViewHandler(dataManager,
                articleListViewModel, attachedArticleListFragment)
    }

    @Test
    fun testOnRefresh() {
        //given
        Mockito.doReturn(false).`when`(articleListViewModel).loading
        Mockito.`when`(dataManager.getArticleList("0.0|1.0")).thenReturn(Single.just(arrayListOf(Article())))
        Mockito.`when`(attachedArticleListFragment.getLocation()).thenReturn(location)
        Mockito.`when`(location.latitude).thenReturn(0.0)
        Mockito.`when`(location.longitude).thenReturn(1.0)

        //when
        articleListViewHandler?.onRefresh()

        //then
        Mockito.verify(dataManager, Mockito.times(1)).getArticleList("0.0|1.0")
    }
}