package com.example.wisdomleaf

import android.content.Context
import com.example.wisdomleaf.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * network manager class to interact with server.
 */
class NetworkManager {

    companion object {
        private val httpClient = OkHttpClient().newBuilder()

        /**
         * initialize retrofit for getting the picsum photos results from server
         */
        fun initializeBaseUrlRetrofit(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            val service = retrofit.create(NetworkService::class.java)
            return service
        }
    }
}