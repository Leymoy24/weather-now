package com.example.weathernow.di

import com.example.weathernow.api.ApiService
import com.example.weathernow.data.repository.MainRepository
import com.example.weathernow.data.repository.MainRepositoryImpl
import com.example.weathernow.data.source.SessionStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService,
        sessionStorage: SessionStorage
    ): MainRepository {
        return MainRepositoryImpl(
            apiService = apiService,
            sessionStorage = sessionStorage
        )
    }

    @Provides
    @Singleton
    fun provideSessionStorage(): SessionStorage {
        return SessionStorage()
    }
}