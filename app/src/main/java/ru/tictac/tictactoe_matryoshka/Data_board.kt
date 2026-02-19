package ru.tictac.tictactoe_matryoshka
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf


data class BoardModel(
    val id: Int,
    var lvl1: String,
    var lvl2: String,
    var lvl3: String,
    var sizeButton: MutableState<Float> = mutableFloatStateOf(0f)
)
