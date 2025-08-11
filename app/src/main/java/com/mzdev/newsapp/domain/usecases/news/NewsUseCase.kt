package com.mzdev.newsapp.domain.usecases.news

data class NewsUseCase(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val getSavedArticle: GetSavedArticle
)