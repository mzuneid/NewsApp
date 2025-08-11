package com.mzdev.newsapp.domain.usecases.news

import com.mzdev.newsapp.domain.model.Article
import com.mzdev.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.getArticle(url = url)
    }

}