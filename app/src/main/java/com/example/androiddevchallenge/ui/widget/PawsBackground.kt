package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.screen.Paw

@Composable
fun PawsBackground(paws: List<Paw>) {
    paws.forEach { paw ->
        Image(
            modifier = Modifier
                .size(140.dp)
                .rotate(paw.rotation)
                .scale(paw.scaleFactor)
                .offset(x = paw.x.dp, y = paw.y.dp),
            painter = painterResource(id = R.drawable.dog_paw),
            contentDescription = "Dog Paw",
            colorFilter = ColorFilter.tint(paw.pawColor)
        )
    }
}

@Preview
@Composable
fun PawsPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    PawsBackground(
        paws = listOf(
            Paw(
                x = 10,
                y = 10,
                scaleFactor = 1f,
                rotation = 0f,
                pawColor = Color(90, 130, 100)
            ),
            Paw(
                x = 80,
                y = 140,
                scaleFactor = 1.2f,
                rotation = 10f,
                pawColor = Color(90, 130, 140)
            )
        ),
    )
}
