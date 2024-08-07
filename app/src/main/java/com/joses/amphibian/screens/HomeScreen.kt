package com.joses.amphibian.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.joses.amphibian.R
import com.joses.amphibian.network.AmphibianData
import com.joses.amphibian.ui.theme.AmphibianTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    data: List<AmphibianData>,
    amphibianUiState: AmphibianUiState
) {
    when (amphibianUiState) {
        is AmphibianUiState.Success -> LazyAmphibianData(modifier = modifier, data = amphibianUiState.data)
        is AmphibianUiState.Error -> ErrorScreen(modifier = modifier)
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier)
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_image),
        contentDescription = null,
        modifier = modifier.size(220.dp)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(
//    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading failed")
        Button(onClick = { /*retryAction*/ } ) {
            Text(text = "Reload")
        }
    }
}

@Composable
fun LazyAmphibianData(
    modifier: Modifier = Modifier,
    data: List<AmphibianData>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(data){ item ->
            AmphibianCard(data = item)
        }
    }
}

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    data: AmphibianData
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = data.name + " (${data.type})",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(data.imgSrc)
                    .crossfade(true)
                    .build(),
//                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = data.description,
//                text = "",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    AmphibianTheme {
        AmphibianCard(
            data = AmphibianData(
                name = "frog 3",
                type = "american",
                description = "dotted",
                imgSrc = ""
            )
        )
    }
}
