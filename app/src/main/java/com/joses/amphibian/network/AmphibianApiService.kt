package com.joses.amphibian.network

import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getData(): List<AmphibianData>
}