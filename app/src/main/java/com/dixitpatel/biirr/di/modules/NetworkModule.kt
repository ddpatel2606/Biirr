package com.dixitpatel.biirr.di.modules

import android.content.Context
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.application.MyApplication
import com.dixitpatel.biirr.constant.BASE_URL
import com.dixitpatel.biirr.constant.HTTP_REQUEST_TIMEOUT
import com.dixitpatel.biirr.network.ApiInterface
import com.dixitpatel.biirr.utils.NullableTypAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.OkHttp3Downloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *  All Network Modules are defined here so they initialized at compileTime.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    // Interceptors are used for displaying logs of API.
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // OkHttpClient for Retrofit and Picasso
    @Provides
    @Singleton
    fun providesClient(loggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor!!)
            .build()
    }



    // Okhttp Downloader.
    @Provides
    fun okHttpDownloader(okHttpClient: OkHttpClient?): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    // Retrofit for Network call Attached with GSONConverterFactory
    @Provides
    fun provideRetrofit(loggingInterceptor: HttpLoggingInterceptor?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapterFactory(NullableTypAdapterFactory()).create()))
            .client(providesClient(loggingInterceptor))
            .build()
    }

    // Retrofit interface for all API call.
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}