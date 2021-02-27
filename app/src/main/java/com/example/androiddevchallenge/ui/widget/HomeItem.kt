package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DataSource
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HomeItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    color: Color
) {
    Card(
        elevation = 8.dp,
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = color
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Row {
                RemoteImage(
                    imageUrl = puppy.imageUrl,
                    contentDescription = "Puppy",
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    showPlaceholder = false
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        puppy.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        puppy.about.short,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        color = Color.White
                    )
                    Text(
                        text = puppy.breed,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        color = Color.White
                    )
                }
            }
        }
    }
}

private val testPuppy = DataSource.puppies.first()

@Preview
@Composable
private fun HomeItemPreviewLight() {
    MyTheme(darkTheme = false) {
        HomeItem(
            puppy = testPuppy, color = Color(
                (90..130).random(),
                90,
                (110..140).random(),
            )
        )
    }
}

@Preview
@Composable
private fun HomeItemPreviewLightDark() {
    MyTheme(darkTheme = true) {
        HomeItem(
            puppy = testPuppy, color = Color(
                (90..130).random(),
                90,
                (110..140).random(),
            )
        )
    }
}
