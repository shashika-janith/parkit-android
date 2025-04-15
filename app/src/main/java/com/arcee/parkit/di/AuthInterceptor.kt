package com.arcee.parkit.di

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.getTokenBlocking()
        val newRequest = chain.request().newBuilder().apply {
            token?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()

        return chain.proceed(newRequest)
    }
}