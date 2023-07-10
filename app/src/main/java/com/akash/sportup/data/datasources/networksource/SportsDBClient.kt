package com.akash.sportup.data.datasources.networksource

import retrofit2.Retrofit
import okhttp3.OkHttpClient

import retrofit2.converter.gson.GsonConverterFactory


/* set up retrofit to fetch data from api */
object SportsDBClient {
    private val httpClient = OkHttpClient.Builder().build()
    private val BASE_URL = "https://www.thesportsdb.com/api/v1/json/50130162/"
    private val serviceHolder =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    fun<T> createService(service: Class<T>) : T{
        return serviceHolder.create(service)
    }
}
