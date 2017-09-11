package com.wiacek.wikipediaarticles.ui.list

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wiacek.wikipediaarticles.R
import com.wiacek.wikipediaarticles.data.db.model.Article
import com.wiacek.wikipediaarticles.databinding.FragmentArticleListItemBinding
import com.wiacek.wikipediaarticles.ui.activity.AttachedArticleListActivity
import com.wiacek.wikipediaarticles.ui.list.item.ItemViewModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

/**
 * Created by wiacek.dawid@gmail.com
 */

class ArticleListAdapter(data: OrderedRealmCollection<Article>?,
                         autoUpdate: Boolean,
                         private val attachedArticleListActivity: AttachedArticleListActivity):
        RealmRecyclerViewAdapter<Article, ArticleListAdapter.ItemViewHolder>(data, autoUpdate) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.fragment_article_list_item, parent, false)

        val githubItemViewModel = ItemViewModel(attachedArticleListActivity = attachedArticleListActivity)

        val binding = FragmentArticleListItemBinding.bind(view)
        binding.setViewModel(githubItemViewModel)

        return ItemViewHolder(view, binding, githubItemViewModel)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    class ItemViewHolder(itemView: View, private val viewDataBinding: ViewDataBinding,
                         private val itemViewModel: ItemViewModel) : RecyclerView.ViewHolder(itemView) {

        internal fun setItem(article: Article?) {
            itemViewModel.setItem(article)
            viewDataBinding.executePendingBindings()
        }
    }
}