package com.example.androiddevchallenge.ui.screen.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.widget.MainScaffold
import com.example.androiddevchallenge.ui.widget.PawsBackground
import com.example.androiddevchallenge.ui.widget.RemoteImage
import com.example.androiddevchallenge.ui.widget.Toolbar

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel
) {
    val context = LocalContext.current
    val priceColor = remember {
        Color(
            (125..255).random(),
            (125..255).random(),
            (125..255).random(),
        )
    }
    val priceShadowColor = remember {
        Color(
            (priceColor.red - (priceColor.red * 0.45f)),
            (priceColor.green - (priceColor.green * 0.45f)),
            (priceColor.blue - (priceColor.blue * 0.45f))
        )
    }
    val paws = detailsViewModel.paws.collectAsState()
    val scrollState = rememberLazyListState()
    MainScaffold(
        topBar = {
            Toolbar(
                title = "",
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                elevation = if (scrollState.firstVisibleItemScrollOffset > 0) {
                    4.dp
                } else {
                    0.dp
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            PawsBackground(paws = paws.value)

            LazyColumn(
                state = scrollState,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .onGloballyPositioned {
                        detailsViewModel.generatePaws(
                            it.size.width /
                                    LocalContext.current
                                        .resources.displayMetrics.density
                        )
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.4f))
            ) {
                detailsViewModel.puppy.apply {
                    item {
                        Header(
                            puppy = this@apply,
                            priceColor = priceColor,
                            priceShadowColor = priceShadowColor
                        )
                    }
                    item {
                        About(bio = about.long)
                    }
                    item {
                        Health(health = health)
                    }
                    item {
                        Location(location = location)
                    }
                    item {
                        Space()
                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Yay!!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }, elevation = ButtonDefaults.elevation(
                                defaultElevation = 8.dp
                            )
                        ) {
                            Text(
                                "Adopt with Love 💖",
                                color = Color.White
                            )
                        }
                        Space()
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(
    puppy: Puppy,
    priceColor: Color,
    priceShadowColor: Color
) {
    Card(elevation = 8.dp) {
        Row(modifier = Modifier.padding(16.dp)) {
            RemoteImage(
                puppy.imageUrl,
                contentDescription = "Puppy image",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Space()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = puppy.name,
                    style = MaterialTheme.typography.h5
                )
                Text(text = "Breed: ${puppy.breed}")
                Text(text = "Age: ${puppy.age}")
                Space()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "\$${puppy.fee}",
                        color = priceColor,
                        modifier = Modifier.rotate(-5f),
                        style = MaterialTheme.typography.h3.copy(
                            shadow = Shadow(
                                color = priceShadowColor,
                                offset = Offset(10f, 10f),
                                blurRadius = 10f
                            )
                        ),
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

@Composable
private fun Space() = Spacer(modifier = Modifier.size(16.dp))

@Composable
private fun About(bio: String) {
    Space()
    Card(elevation = 8.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Subtitle(text = "About me 💕💕")
            Space()
            Text(text = bio)
        }
    }
}

@Composable
private fun Health(health: Puppy.Health) {
    Space()
    Card(elevation = 8.dp) {
        Column {
            Subtitle(
                text = "Health",
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            HealthRow(text = "Desexed", done = health.deSexed)
            HealthRow(text = "Vaccinated", done = health.vaccinated)
            HealthRow(text = "Wormed", done = health.wormed)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
private fun HealthRow(text: String, done: Boolean) {
    Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Text(
            text = text,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(
                id = if (done) {
                    R.drawable.yep
                } else {
                    R.drawable.nope
                }
            ),
            modifier = Modifier.size(20.dp),
            contentDescription = "Health status"
        )
    }
}

@Composable
private fun Location(location: Puppy.Location) {
    Space()
    Card(elevation = 8.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Subtitle(text = "Location")
            Space()
            RemoteImage(
                imageUrl = generateLocationImageUrl(
                    location,
                    isSystemInDarkTheme()
                ),
                contentDescription = "Location",
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .widthIn(min = 0.dp, max = 200.dp)
            )
        }
    }
}

@Composable
private fun Subtitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle2,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

private fun generateLocationImageUrl(
    location: Puppy.Location,
    darkMode: Boolean
): String {
    return StringBuilder()
        .append("https://maps.googleapis.com/maps/api/staticmap?")
        .append("center=${location.lat},${location.lng}")
        .append("&zoom=14&size=400x400")
        .append("&markers=color:pink%7Clabel:S%7C${location.lat},${location.lng}")
        .appendIf("&style=element:geometry%%7Cinvert_lightness:true", darkMode)
        .appendIf(
            "&style=feature:landscape.natural.terrain%%7Celement:geometry%%7Cvisibility:on",
            darkMode
        )
        .appendIf("&style=feature:landscape%%7Celement:geometry.fill%%7Ccolor:0x303030", darkMode)
        .appendIf("&style=feature:poi%%7Celement:geometry.fill%%7Ccolor:0x404040", darkMode)
        .appendIf("&style=feature:poi.park%%7Celement:geometry.fill%%7Ccolor:0x0a330a", darkMode)
        .appendIf("&style=feature:water%%7Celement:geometry%%7Ccolor:0x00003a", darkMode)
        .appendIf(
            "&style=feature:transit%%7Celement:geometry%%7Cvisibility:on%%7Ccolor:0x101010",
            darkMode
        )
        .appendIf("&style=feature:road%%7Celement:geometry.stroke%%7Cvisibility:on", darkMode)
        .appendIf("&style=feature:road.local%%7Celement:geometry.fill%%7Ccolor:0x606060", darkMode)
        .appendIf(
            "&style=feature:road.arterial%%7Celement:geometry.fill%%7Ccolor:0x888888",
            darkMode
        )
        .append("&key=_")
        .toString()
}

private fun StringBuilder.appendIf(text: String, condition: Boolean): StringBuilder {
    if (condition) append(text)
    return this
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        navController = rememberNavController(),
        detailsViewModel = DetailsViewModel("1")
    )
}
