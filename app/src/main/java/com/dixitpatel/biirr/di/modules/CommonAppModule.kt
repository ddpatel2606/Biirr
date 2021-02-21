package com.dixitpatel.biirr.di.modules

import android.content.Context
import com.dixitpatel.biirr.application.MyApplication
import com.dixitpatel.biirr.utils.NullableTypAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonAppModule {

    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
         return GsonBuilder()
             .registerTypeAdapterFactory(NullableTypAdapterFactory())
             .create()
    }
}