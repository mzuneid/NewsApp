package com.mzdev.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mzdev.newsapp.data.local.NewsDao
import com.mzdev.newsapp.data.remote.NewsAPI
import com.mzdev.newsapp.data.remote.NewsPagingSource
import com.mzdev.newsapp.data.remote.SearchNewsPagingSource
import com.mzdev.newsapp.domain.model.Article
import com.mzdev.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI,
    private val newsDao: NewsDao
) : NewsRepository{


    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsAPI,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsAPI,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticleByUrl()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticleByUrl(url = url)
    }


}