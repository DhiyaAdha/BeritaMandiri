package com.example.beritamandiri.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamandiri.R
import com.example.beritamandiri.data.remote.ApiClient
import com.example.beritamandiri.data.response.ArticlesItem
import com.example.beritamandiri.data.response.NewsResponse
import com.example.beritamandiri.ui.detail.DetailActivity
import com.example.beritamandiri.ui.detail.DetailActivity.Companion.EXTRA_NEWS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
            // Handle onClick event
            // Implement your click logic here
            moveActivity(it)
        }

        val rvNews: RecyclerView = findViewById(R.id.rvNews)

        // Menggunakan 'this' karena sudah berada dalam konteks Activity
        rvNews.layoutManager = LinearLayoutManager(this)

        // Menggunakan lambd expression untuk adapter jika Anda memiliki konstruktor yang sesuai di NewsAdapter
        rvNews.adapter = adapter
    }

    private fun moveActivity(news: ArticlesItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
    }

    private fun getNews() {
        val apiClient = ApiClient.create()

        apiClient.getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles.orEmpty() // Mengatasi kemungkinan nilai null dengan menggunakan orEmpty()
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
