package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


    private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    //membuat objek moshi
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //ini builder Retrofit untuk membuat objek Retrofit
    private val retrofit = Retrofit.Builder()
        //akan mengambil respon dari JSON dari web dan ditampilkan menjadi string
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    //menentukan cara Retrofit berkomunikasi dengan web server
    interface MarsApiService {
        @GET("photos")
        fun getPhotos(): List<MarsPhoto>
    }

    //membuat objek singleton yang dipanggil saat akses pertama
    object MarsApi {
        val retrofitService : MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java) }
    }
