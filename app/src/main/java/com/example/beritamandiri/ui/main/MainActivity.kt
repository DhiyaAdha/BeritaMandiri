package com.example.beritamandiri.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamandiri.R
import com.example.beritamandiri.data.remote.ApiClient
import com.example.beritamandiri.data.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvNews: RecyclerView = findViewById(R.id.rvNews)

        // Menggunakan 'this' karena sudah berada dalam konteks Activity
        rvNews.layoutManager = LinearLayoutManager(this)

        // Menggunakan lambd expression untuk adapter jika Anda memiliki konstruktor yang sesuai di NewsAdapter
        rvNews.adapter = NewsAdapter {
            // Handle onClick event
        }
    }

    private fun getNews() {
        val apiClient = ApiClient.create()

        apiClient.getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("MainActivity", response.body().toString())
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
