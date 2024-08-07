package com.joses.amphibian.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.joses.amphibian.AmphibianDataApplication
import com.joses.amphibian.data.AmphibianDataRepository
import com.joses.amphibian.data.NetworkAmphibianDataRepository
import com.joses.amphibian.network.AmphibianData
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val data: List<AmphibianData>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibianViewModel(
    private val amphibianDataRepository: AmphibianDataRepository
) : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibianData()
    }

    val data = listOf(
        AmphibianData(
            name = "Pacific Chorus Frog",
            type = "Frog",
            description = "Also known as the Pacific Tree frog, it is the most common frog on the Pacific Coast of North America. " +
                    "These frogs can vary in color between green and brown and can be identified by a brown stripe that runs from their nostril, through the eye, to the ear.",
            imgSrc = ""
        ),
        AmphibianData(
            name = "frog 2",
            type = "african",
            description = "non-poisonous",
            imgSrc = ""
        ),
        AmphibianData(
            name = "frog 3",
            type = "american",
            description = "dotted",
            imgSrc = ""
        ),
        AmphibianData(
            name = "frog 4",
            type = "jamaican",
            description = "Froggy man",
            imgSrc = ""
        ),
    )

    private fun getAmphibianData() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianDataRepository.getAmphibianData())
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianDataApplication)
                val amphibianDataRepository = application.container.amphibianDataRepository
                AmphibianViewModel(amphibianDataRepository = amphibianDataRepository)
            }
        }
    }

}