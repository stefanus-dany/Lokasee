package com.bangkit.lokasee.data.retrofit

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bangkit.lokasee.data.AppPreferences
import com.bangkit.lokasee.data.store.UserStore.currentUserToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    const val HOST: String = "http://34.77.53.105"

    fun getApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = if(currentUserToken != ""){
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $currentUserToken")
                    .build()
                chain.proceed(newRequest)
            }).addInterceptor(loggingInterceptor).build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("$HOST/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}