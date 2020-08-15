package com.axxess.cavistaassignment.repositry

import com.axxess.cavistaassignment.model.SearchImageModel
import retrofit2.Response

interface Repository {

    suspend fun searchImage(page: Int, query: String): Response<SearchImageModel>

}