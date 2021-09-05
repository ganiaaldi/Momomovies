package com.gadidev.momomovies.di

import android.content.Context
import androidx.room.Room
import com.gadidev.momomovies.repo.AppDatabase
import com.gadidev.momomovies.repo.MoviesDao
import com.gadidev.momomovies.repo.MoviesRepository
import com.gadidev.momomovies.services.ApiService
import com.gadidev.momomovies.services.MoviesRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api-lk21.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(movieService: ApiService) = MoviesRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
//    @ViewModelScoped
    fun provideRepository(remoteDataSource: MoviesRemoteDataSource,
                          localDataSource: MoviesDao) =
        MoviesRepository(remoteDataSource, localDataSource)
}
