package com.mzdev.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzdev.newsapp.domain.model.Article
import com.mzdev.newsapp.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.getSavedArticle(event.article.url)
                    if(article == null){
                        upsertArticle(event.article)
                    }else {
                        deleteArticle(event.article)
                    }
                }
            }

            DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }
    private suspend fun upsertArticle(article: Article) {
        newsUseCase.upsertArticle(article)
        sideEffect = "Article Saved"
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCase.deleteArticle(article)
        sideEffect = "Article Deleted"
    }


}

