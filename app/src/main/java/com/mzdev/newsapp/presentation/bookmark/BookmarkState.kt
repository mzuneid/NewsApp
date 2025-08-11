package com.mzdev.newsapp.presentation.bookmark

import com.mzdev.newsapp.domain.model.Article

data class BookmarkState(
    val articles : List<Article> = emptyList()
)
