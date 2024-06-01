package com.rlibanez.retrofitsample.data

import com.rlibanez.retrofitsample.data.model.SectionResult
import com.rlibanez.retrofitsample.data.model.Section
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("api/sections")
    suspend fun getSections(): SectionResult

    @GET("api/sections/{name}")
    suspend fun getSection(
        @Path("name") name: String
    ): Section
}
