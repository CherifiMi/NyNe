package com.example.nyne.domein.repo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class BookRepository {
    private lateinit var baseApiUrl: String
    private val googleBooksUrl = "https://www.googleapis.com/books/v1/volumes"
    private val googleApiKey = "AIzaSyBCaXx-U0sbEpGVPWylSggC4RaR4gCGkVE"

    private val okHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()

    private val gsonClient = Gson()

}