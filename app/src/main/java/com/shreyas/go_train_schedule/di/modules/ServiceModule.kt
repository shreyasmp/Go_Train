package com.shreyas.go_train_schedule.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shreyas.go_train_schedule.api.GoApi
import com.shreyas.go_train_schedule.di.annotations.ApiKey
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ServiceModule {

    private const val METROLINX_GO_BASE_URL = "https://api.openmetrolinx.com/OpenDataAPI/api/V1/"

    private const val METROLINX_GO_API_KEY = "30024841"

    @Provides
    @ApiKey
    fun provideApiKey(): String {
        return METROLINX_GO_API_KEY
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApiKey apiKey: String,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", apiKey)
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request = request)
            }
            .build()
    }

    @Provides
    internal fun provideGoMetrolinxService(okHttpClient: OkHttpClient): GoApi {
        return Retrofit.Builder()
            .baseUrl(METROLINX_GO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(GoApi::class.java)
    }
}