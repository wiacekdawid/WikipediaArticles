package com.wiacek.wikipediaarticles.ui.list

import android.graphics.Rect
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wiacek.wikipediaarticles.databinding.FragmentArticleListBinding
import com.wiacek.wikipediaarticles.di.modules.ArticleListFragmentModule
import com.wiacek.wikipediaarticles.ui.activity.ArticleListActivity
import com.wiacek.wikipediaarticles.ui.activity.AttachedArticleListActivity
import org.parceler.Parcels
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class ArticleListFragment: Fragment() {
    private val BUNDLE_LIST_VIEW_MODEL = "BUNDLE_LIST_VIEW_MODEL"
    private val BUNDLE_LINEAR_LAYOUT_MANAGER_STATE = "BUNDLE_LINEAR_LAYOUT_MANAGER_STATE"

    @Inject
    lateinit var articleListViewHandler: ArticleListViewHandler
    @Inject
    lateinit var attachedArticleListActivity: AttachedArticleListActivity

    private var fragmentArticleListBinding: FragmentArticleListBinding? = null
    private var articleListAdapter: ArticleListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (activity as ArticleListActivity)
                .getArticleListActivityComponent()?.add(ArticleListFragmentModule(this))
        component?.inject(this)
        if (savedInstanceState != null) {
            savedInstanceState
                    .getParcelable<Parcelable>(BUNDLE_LIST_VIEW_MODEL)?.let {
                articleListViewHandler.articleListViewModel = Parcels.unwrap<ArticleListViewModel>(it)
            }
        }
        articleListViewHandler.onAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
        articleListViewHandler.onDetach()
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        linearLayoutManager = LinearLayoutManager(activity)
        if (savedInstanceState != null) {
            linearLayoutManager?.onRestoreInstanceState(savedInstanceState.getParcelable(BUNDLE_LINEAR_LAYOUT_MANAGER_STATE))
        }
        fragmentArticleListBinding = FragmentArticleListBinding.inflate(inflater, container, false)
        fragmentArticleListBinding?.viewModel = articleListViewHandler.articleListViewModel
        fragmentArticleListBinding?.viewHandler = articleListViewHandler
        setupRecyclerView()
        return fragmentArticleListBinding?.root
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(BUNDLE_LINEAR_LAYOUT_MANAGER_STATE, linearLayoutManager?.onSaveInstanceState())
        outState?.putParcelable(BUNDLE_LIST_VIEW_MODEL, Parcels.wrap(articleListViewHandler.articleListViewModel))
    }
    private fun setupRecyclerView() {
        val recyclerView = fragmentArticleListBinding?.fragmentListRecyclerView
        articleListAdapter = ArticleListAdapter(articleListViewHandler.getAdapterDataFromDb(), true, attachedArticleListActivity)
        recyclerView?.adapter = articleListAdapter
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.addItemDecoration(
                object : DividerItemDecoration(activity, linearLayoutManager?.orientation ?: LinearLayoutManager(activity).orientation) {
                    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                        val position = parent!!.getChildAdapterPosition(view)
                        // hide the divider for the last child
                        if (position == parent.adapter.itemCount - 1) {
                            outRect.setEmpty()
                        } else {
                            super.getItemOffsets(outRect, view, parent, state)
                        }
                    }
                }
        )
    }
}