package com.example.beritamandiri.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button  // Add this import statement
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beritamandiri.R
import com.example.beritamandiri.data.response.ArticlesItem

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS = "extraNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)
        news?.let { it ->
            initView(it)
        }
    }

    private fun initView(news: ArticlesItem) {
        if (news != null) {
            Log.d("DetailActivity", news.author.toString())

            // Pemanggilan data
            val tvTitle = findViewById<TextView>(R.id.tvTitle)
            val tvAuthor = findViewById<TextView>(R.id.tvAuthor)
            val tvSourceName = findViewById<TextView>(R.id.tvSourceName)
            val ivNews = findViewById<ImageView>(R.id.ivNews)
            val btnToNews = findViewById<Button>(R.id.btnToNews)  // Add this line

            tvTitle.text = news.title
            tvAuthor.text = news.author
            tvSourceName.text = news.source?.name

            Glide.with(this@DetailActivity)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_img))
                .into(ivNews)

            btnToNews.setOnClickListener { view ->
                openWebPage(news.url)
            }

            openWebPage(news.url)
        } else {
            Log.e("DetailActivity", "News is null")
        }
    }

    private fun openWebPage(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
