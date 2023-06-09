package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto (
    //membuat variabel yang sesuai dengan nama kunci
    //dalam objek JSON
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)