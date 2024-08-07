package com.joses.amphibian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joses.amphibian.network.AmphibianData
import com.joses.amphibian.screens.AmphibianViewModel
import com.joses.amphibian.screens.HomeScreen
import com.joses.amphibian.ui.theme.AmphibianTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibianTheme {
                val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text ="Amphibian") },
                            actions = {
                                Button(
                                    onClick = {
                                        imageLoader.diskCache?.clear()
                                        imageLoader.memoryCache?.clear()
                                    }
                                ) {
                                    Text(text = "Clear Cache")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        data = amphibianViewModel.data,
                        amphibianUiState = amphibianViewModel.amphibianUiState
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmphibianTheme {
        val fakeData = listOf(
            AmphibianData(
                name = "frog 1",
                type = "asian",
                description = "poisonous",
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
            )
        )
//        HomeScreen(data = fakeData, amphibianUiState = )
    }
}