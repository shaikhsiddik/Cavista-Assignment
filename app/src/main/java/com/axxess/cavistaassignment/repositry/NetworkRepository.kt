package com.axxess.cavistaassignment.repositry

import com.axxess.cavistaassignment.model.SearchImageModel
import com.axxess.cavistaassignment.network.Api
import retrofit2.Response

object NetworkRepository : Repository {
    override suspend fun searchImage(
        page: Int, query: String
    ): Response<SearchImageModel> {
        return Api.http.searchImage(page, query)
    }


}