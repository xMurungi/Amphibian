package com.joses.amphibian.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.joses.amphibian.network.AmphibianApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibianDataRepository: AmphibianDataRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    override val amphibianDataRepository: AmphibianDataRepository by lazy {
        NetworkAmphibianDataRepository(retrofitService)
    }

}