package com.rlibanez.retrofitsample.data

import com.rlibanez.retrofitsample.data.model.RemoteResult
import com.rlibanez.retrofitsample.data.model.Section
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("api/sections")
    suspend fun getSections(): RemoteResult

    @GET("api/sections/{name}")
    suspend fun getSection(
        @Path("name") name: String
    ): Section
}

object RetrofitServiceFactory {

    private const val BASE_URL = "http://192.168.2.2:8008/"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}