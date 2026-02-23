package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


data class BoardModel(
    val id: String,
    val neighbors: String,
    var lvl1: String = "",
    var lvl2: String = "",
    var lvl3: String = "",
    var colorNow: MutableState<Color> = mutableStateOf(Color.Unspecified),
    var sizeNow: MutableState<Float> = mutableFloatStateOf(0f),
    var buttonBackground: MutableState<Color> = mutableStateOf(Color.White),
    var enabled: MutableState<Boolean> = mutableStateOf(true),
)
