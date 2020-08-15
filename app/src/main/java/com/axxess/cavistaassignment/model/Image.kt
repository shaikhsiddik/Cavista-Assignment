package com.axxess.cavistaassignment.model

import java.io.Serializable

data class Image(
    val ad_type: Int,
    val ad_url: String,
    val animated: Boolean,
    val bandwidth: Long,
    val datetime: Long,
    val edited: String,
    val favorite: Boolean,
    val gifv: String,
    val has_sound: Boolean,
    val height: Int,
    val hls: String,
    val id: String,
    val in_gallery: Boolean,
    val in_most_viral: Boolean,
    val is_ad: Boolean,
    val link: String,
    val looping: Boolean,
    val mp4: String,
    val mp4_size: Int,
    val processing: Processing,
    val size: Int,
    val type: String,
    val views: Int,
    val width: Int
) : Serializable