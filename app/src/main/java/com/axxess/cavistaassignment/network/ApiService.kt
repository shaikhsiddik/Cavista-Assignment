package com.axxess.cavistaassignment.network

import com.axxess.cavistaassignment.model.SearchImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/gallery/search/{page}")
    @Headers("Authorization:Client-ID 137cda6b5008a7c")
    suspend fun searchImage(
        @Path("page") page: Int,
        @Query("q") query: String
    ): Response<SearchImageModel>
}