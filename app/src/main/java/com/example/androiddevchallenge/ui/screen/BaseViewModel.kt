package com.example.androiddevchallenge.ui.screen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.min
import kotlin.random.Random

open class BaseViewModel : ViewModel() {
    val paws = MutableStateFlow<List<Paw>>(listOf())

    fun generatePaws(width: Float) {
        if (paws.value.isNotEmpty()) return
        paws.value = (0..3).map {
            Paw(
                x = (0..width.toInt()).random(),
                y = (it * 140),
                scaleFactor = 1f + min(Random.nextFloat(), 0.2f),
                rotation = (-10..20).random().toFloat(),
                pawColor = Color(
                    (125..255).random(),
                    (125..255).random(),
                    (125..255).random(),
                )
            )
        }
    }
}
data class Paw(
    val x: Int,
    val y: Int,
    val scaleFactor: Float,
    val rotation: Float,
    val pawColor: Color
)
