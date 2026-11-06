package com.example.artspaceapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentArtwork by remember { mutableStateOf(1) }

    val titleRes = when (currentArtwork) {
        1 -> R.string.artwork_title_1
        2 -> R.string.artwork_title_2
        else -> R.string.artwork_title_3
    }
    val authorRes = when (currentArtwork) {
        1 -> R.string.artwork_author_1
        2 -> R.string.artwork_author_2
        else -> R.string.artwork_author_3
    }
    val imageRes = when (currentArtwork) {
        1 -> R.drawable.art1
        2 -> R.drawable.art2
        else -> R.drawable.art3
    }

    val isWide = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isWide) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtworkImage(imageRes, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkInfo(titleRes, authorRes)
                Spacer(modifier = Modifier.height(16.dp))
                NavigationButtons(
                    onPrevious = { if (currentArtwork > 1) currentArtwork-- },
                    onNext = { if (currentArtwork < 3) currentArtwork++ }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ArtworkImage(imageRes, Modifier.weight(1f))
            Spacer(modifier = Modifier.height(16.dp))
            ArtworkInfo(titleRes, authorRes)
            Spacer(modifier = Modifier.height(16.dp))
            NavigationButtons(
                onPrevious = { if (currentArtwork > 1) currentArtwork-- },
                onNext = { if (currentArtwork < 3) currentArtwork++ }
            )
        }
    }
}

@Composable
fun ArtworkImage(imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
    )
}

@Composable
fun ArtworkInfo(titleRes: Int, authorRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(1.dp, Color.Gray)
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = stringResource(authorRes),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Composable
fun NavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE0E0E0),
                contentColor = Color.Black
            ),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Text(stringResource(R.string.previous))
        }
        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE0E0E0),
                contentColor = Color.Black
            ),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    MaterialTheme {
        ArtSpaceApp()
    }
}