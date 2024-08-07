package com.example.oduksearcher.network

import com.example.oduksearcher.BuildConfig
import com.example.oduksearcher.data.remote.SearchMedia
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.Throws

private const val KAKAO_API_KEY = "accc305bf542c579bf0ebf65d47bf238"
object Retrofit {
    private const val BASE_URL = "https://dapi.kakao.com/v2/search/"
    val kakaoNetwork: SearchMedia by lazy { getClient().create(SearchMedia::class.java) }

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(NetworkInterceptor()))
            .build()
    }

    private fun getOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                build()
            }
    }

    class NetworkInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "KakaoAK ${KAKAO_API_KEY}")
                .build()
            return chain.proceed(newRequest)
        }
    }
}