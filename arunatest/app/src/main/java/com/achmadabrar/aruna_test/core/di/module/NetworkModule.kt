package com.tokopedia.searchonboardingtokped.core.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.tokopedia.searchonboardingtokped.BuildConfig
import com.tokopedia.searchonboardingtokped.data.database.SearchDatabase
import com.tokopedia.searchonboardingtokped.data.network.SearchApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providePostApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl("https://gql.tokopedia.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = SearchDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideSearchProductDao(searchProductDb: SearchDatabase) = searchProductDb.searchProductDao()

    @Singleton
    @Provides
    fun provideSearchShopDao(searchShopDb: SearchDatabase) = searchShopDb.searchShopDao()
}