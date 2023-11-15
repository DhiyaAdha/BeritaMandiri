package com.example.beritamandiri.data.remote

import com.example.beritamandiri.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("top-headlines?country=us&apiKey=a01da0e99b1d482c8af83334e5f7b8c1")
    fun getNews(): Call<NewsResponse>
}
