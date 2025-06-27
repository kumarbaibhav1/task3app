package com.example.task3app.network

import com.example.task3app.model.ZenQuote
import retrofit2.http.GET

interface QuoteApiService {
    @GET("api/random")
    suspend fun getRandomQuote(): List<ZenQuote>
}
