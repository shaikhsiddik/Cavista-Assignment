package com.axxess.cavistaassignment.model

import com.axxess.cavistaassignment.model.Data

data class SearchImageModel(
    val data: ArrayList<Data>,
    val status: Int,
    val success: Boolean
)