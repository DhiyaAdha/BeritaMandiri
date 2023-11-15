package com.example.beritamandiri.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamandiri.R
import com.example.beritamandiri.data.response.ArticlesItem

class NewsAdapter(private val listener: (ArticlesItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news: MutableList<ArticlesItem> = mutableListOf()

    fun setNews(news: MutableList<ArticlesItem>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Implement the creation of your ViewHolder here
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        // Implement the logic to return the number of items in your data set
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your ViewHolder implementation goes here
        fun bind(news: ArticlesItem) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

            if (tvTitle != null) {
                tvTitle.text = news.title
            } else {
                Log.e("NewsAdapter", "tvTitle not found in itemView")
            }

            itemView.setOnClickListener {
                listener(news)
            }
        }
    }
}
