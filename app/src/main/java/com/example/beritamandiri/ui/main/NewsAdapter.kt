package com.example.beritamandiri.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beritamandiri.R
import com.example.beritamandiri.data.response.ArticlesItem

class NewsAdapter(private val listener: (ArticlesItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem>()

    fun setNews(news: List<ArticlesItem?>) {
        this.news = news as List<ArticlesItem>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(newsItem: ArticlesItem) {
            itemView.findViewById<TextView>(R.id.tvTitle)?.text = newsItem.title

            Glide.with(itemView.context)
                .load(newsItem.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_img))
                .into(itemView.findViewById(R.id.ivNews))

            itemView.setOnClickListener {
                listener(newsItem)
            }
        }
    }

}
