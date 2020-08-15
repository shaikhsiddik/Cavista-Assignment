package com.axxess.cavistaassignment.ext

import com.axxess.cavistaassignment.model.Data

fun Data.getImageUrl(): String {
    return if (this.images.isNullOrEmpty()) {
        this.link;
    } else {
        this.images[0].link;
    }


}
