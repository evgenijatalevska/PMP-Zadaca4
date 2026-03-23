package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme

private data class DrawableStringPair(@DrawableRes val drawable: Int, val text: String)

private val alignYourBodyData = listOf(
    DrawableStringPair(R.drawable.ab1_inversions, "Inversions"),
    DrawableStringPair(R.drawable.ab2_quick_yoga, "Quick Yoga"),
    DrawableStringPair(R.drawable.ab3_stretching, "Stretching"),
    DrawableStringPair(R.drawable.ab4_tabata, "Tabata"),
    DrawableStringPair(R.drawable.ab5_hiit, "HIIT"),
    DrawableStringPair(R.drawable.ab6_pre_natal_yoga, "Pre-natal Yoga")
)

private val favoriteCollectionsData = listOf(
    DrawableStringPair(R.drawable.fc1_short_mantras, "Short Mantras"),
    DrawableStringPair(R.drawable.fc2_nature_meditations, "Nature Meditations"),
    DrawableStringPair(R.drawable.fc3_stress_and_anxiety, "Stress and Anxiety"),
    DrawableStringPair(R.drawable.fc4_self_massage, "Self Massage"),
    DrawableStringPair(R.drawable.fc5_overwhelmed, "Overwhelmed"),
    DrawableStringPair(R.drawable.fc6_nightly_wind_down, "Nightly Wind Down")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheTheme {
                Scaffold(
                    bottomBar = { SootheBottomNavigation() }
                ) { innerPadding ->
                    HomeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = { Text("Search") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Ова ја исполнува сликата во кругот
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar()

        HomeSection(title = "Align Your Body") {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(alignYourBodyData) { item ->
                    AlignYourBodyElement(item.drawable, item.text)
                }
            }
        }

        HomeSection(title = "Favorite Collections") {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.height(168.dp)
            ) {
                items(favoriteCollectionsData) { item ->
                    FavoriteCollectionCard(item.drawable, item.text)
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Spa, null) },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, null) },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
    }
}