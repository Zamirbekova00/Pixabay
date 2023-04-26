package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    fun getImage(
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 3,
        @Query("key") key: String = "35786050-c5c3ebe0fba8a2b3e51f5634f"

    ): Call<PixaModel>
}