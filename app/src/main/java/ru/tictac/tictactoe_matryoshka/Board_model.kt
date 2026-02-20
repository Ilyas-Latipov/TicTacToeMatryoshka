package ru.tictac.tictactoe_matryoshka
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


data class BoardModel(
    val id: Int,
    var lvl1: String,
    var lvl2: String,
    var lvl3: String,
    var color: MutableState<Color> = mutableStateOf(Color.Unspecified),
    var sizeButton: MutableState<Float> = mutableFloatStateOf(0f),
)
