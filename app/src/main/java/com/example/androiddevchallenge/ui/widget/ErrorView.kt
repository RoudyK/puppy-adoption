package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun ErrorView(onclick: () -> Unit) {
    MyTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sad_puppy),
                contentDescription = "Error Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Woof :(")
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = onclick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                ),
            ) {
                Text("I love dogs <3")
            }
        }
    }
}

@Preview
@Composable
private fun ErrorViewLight() {
    MyTheme(darkTheme = false) {
        ErrorView {}
    }
}

@Preview
@Composable
private fun ErrorViewDark() {
    MyTheme(darkTheme = true) {
        ErrorView {}
    }
}
