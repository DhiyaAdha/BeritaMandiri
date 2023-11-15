package com.example.beritamandiri.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beritamandiri.R
import com.example.beritamandiri.data.response.ArticlesItem

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_NEWS = "extraNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

        Log.d("DetailActivity", news!!.author.toString())

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = news.title
    }
}
