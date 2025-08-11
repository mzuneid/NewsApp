package com.mzdev.newsapp.data.remote.dto

import com.mzdev.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)