package com.mzdev.newsapp.domain.usecases.news

import com.mzdev.newsapp.domain.model.Article
import com.mzdev.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}