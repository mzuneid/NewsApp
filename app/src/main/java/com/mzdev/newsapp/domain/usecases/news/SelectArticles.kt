package com.mzdev.newsapp.domain.usecases.news

import com.mzdev.newsapp.domain.model.Article
import com.mzdev.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

     operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}