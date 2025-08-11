package com.mzdev.newsapp.di

import android.app.Application
import androidx.room.Room
import com.mzdev.newsapp.data.local.NewsDao
import com.mzdev.newsapp.data.local.NewsDatabase
import com.mzdev.newsapp.data.local.NewsTypeConvertor
import com.mzdev.newsapp.data.manager.LocalUserManagerImpl
import com.mzdev.newsapp.data.remote.NewsAPI
import com.mzdev.newsapp.data.repository.NewsRepositoryImpl
import com.mzdev.newsapp.domain.manager.LocalUserManager
import com.mzdev.newsapp.domain.repository.NewsRepository
import com.mzdev.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.mzdev.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.mzdev.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.mzdev.newsapp.domain.usecases.news.DeleteArticle
import com.mzdev.newsapp.domain.usecases.news.GetNews
import com.mzdev.newsapp.domain.usecases.news.GetSavedArticle
import com.mzdev.newsapp.domain.usecases.news.NewsUseCase
import com.mzdev.newsapp.domain.usecases.news.SearchNews
import com.mzdev.newsapp.domain.usecases.news.SelectArticles
import com.mzdev.newsapp.domain.usecases.news.UpsertArticle
import com.mzdev.newsapp.util.Constants.BASE_URL
import com.mzdev.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideApiInstance(): NewsAPI {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsAPI,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            getSavedArticle = GetSavedArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}