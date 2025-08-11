package com.mzdev.newsapp.data.remote

import com.mzdev.newsapp.BuildConfig
import com.mzdev.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : NewsResponse
}