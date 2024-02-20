package com.ruzgaruludag.rickandmorty.newtask.networking

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            var request = chain.request()
            chain.proceed(request)
        }catch (e:Exception){
            chain.proceed(chain.request())
        }
    }
}