package com.kgb.news

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.kgb.news.model.Article
import com.kgb.news.modules.GlideApp
import com.kgb.news.utils.DateUtils
import kotlinx.android.synthetic.main.item_home_news.view.*

/**
 * @author Krzysztof Betlej <labiod@wp.pl>.
 * @date 5/23/18
 */
class HomeNewsAdapter(private val newsArticles: List<Article>): RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {

    class HomeNewsViewHolder(val root: View) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_home_news, parent, false)
        return HomeNewsViewHolder(root)
    }

    override fun getItemCount(): Int = newsArticles.size

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        GlideApp.with(holder.root.context)
                .load(newsArticles[position].urlToImage)
                .centerCrop()
                //.placeholder(R.drawable.sample)
                .into(holder.root.card_news_image)
        holder.root.card_news_title.text = newsArticles[position].title
        holder.root.card_news_time.text = DateUtils.formatNewsApiDate(newsArticles[position].publishedAt ?: "")
        holder.root.card_news_content.text = newsArticles[position].description
        holder.itemView.setOnClickListener {
            // log analytics
            val firebaseAnalytics = FirebaseAnalytics.getInstance(it.context)
            val bundle = Bundle()
            bundle.putString("index", position.toString())
            firebaseAnalytics.logEvent("cardClicked", bundle)
            NewsDetailsActivity.launch(it.context, position)
        }
    }

}