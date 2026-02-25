package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


data class BoardModel(
    val id: String,
    val neighbors: String,
    var buttonBackground: MutableState<Color>,
    var lvl1: String = "",
    var lvl2: String = "",
    var lvl3: String = "",
    var colorNow: MutableState<Color> = mutableStateOf(Color.Unspecified),
    var sizeNow: MutableState<Float> = mutableFloatStateOf(0f),
    var enabled: MutableState<Boolean> = mutableStateOf(true),
)
