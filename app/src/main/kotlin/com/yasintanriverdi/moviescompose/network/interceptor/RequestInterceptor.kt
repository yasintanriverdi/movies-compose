package com.yasintanriverdi.moviescompose.network.interceptor

import com.yasintanriverdi.moviescompose.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_NAME_API_KEY, BuildConfig.TMDB_API_KEY)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val QUERY_NAME_API_KEY = "api_key"
    }
}
