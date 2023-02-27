package com.grand.navigation.network

import com.grand.navigation.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(): SearchResponse

    @GET("products/search?")
    suspend fun search(@Query("q") name:String): SearchResponse

}