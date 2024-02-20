package com.ruzgaruludag.rickandmorty.newtask.networking

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ruzgaruludag.rickandmorty.newtask.utils.Constant
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2core.Downloader
import com.tonyodev.fetch2okhttp.OkHttpDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// TODO: 20.02.2024 api module

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



    @Provides
    @Singleton
    fun provideHomeApiInterface(retrofit: Retrofit): NetworkManager =
        retrofit.create(NetworkManager::class.java)

    @Singleton
    @Provides
    fun provideFetch(config: FetchConfiguration): Fetch = Fetch.Impl.getInstance(config)

    @Singleton
    @Provides
    fun provideFetchConfiguration(
        @ApplicationContext context: Context
    ): FetchConfiguration = FetchConfiguration.Builder(context)
        .enableRetryOnNetworkGain(true)
        .setHttpDownloader(
            OkHttpDownloader(
                OkHttpClient.Builder().build(),
                Downloader.FileDownloaderType.SEQUENTIAL
            )
        )
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClientAuth(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): Retrofit {

        val newGson = GsonBuilder()
            .setLenient()
            .create()


        return Retrofit.Builder()
            .baseUrl(Constant.url)
            .addConverterFactory(GsonConverterFactory.create(newGson))
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        authenticationInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        okHttpClient.addInterceptor(authenticationInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideAuthInter(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

}