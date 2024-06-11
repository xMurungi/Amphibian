package com.joses.amphibian.data

import com.joses.amphibian.network.AmphibianApiService
import com.joses.amphibian.network.AmphibianData

interface AmphibianDataRepository {
    suspend fun getAmphibianData(): List<AmphibianData>
}

class NetworkAmphibianDataRepository(
    private val amphibianApiService: AmphibianApiService
): AmphibianDataRepository {
    override suspend fun getAmphibianData(): List<AmphibianData> = amphibianApiService.getData()
}