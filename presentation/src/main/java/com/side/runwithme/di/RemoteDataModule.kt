package com.side.runwithme.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.side.data.api.LoginApi
import com.side.runwithme.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginHeaderOkhttp

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginHeaderRetrofit

    // HttpLoggingInterceptor DI
    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //    OkHttpClient DI
    @Provides
    @Singleton
    @LoginHeaderOkhttp
    fun provideLoginHeaderOkHttpClient(
//        loginInterceptor: LoginInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .addNetworkInterceptor(loginInterceptor)
            .build()
    }

    // Gson DI
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    //Retrofit DI
    @Provides
    @Singleton
    @LoginHeaderRetrofit
    fun provideLoginHeaderRetrofitInstance(@LoginHeaderOkhttp client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApi(@LoginHeaderRetrofit retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

}