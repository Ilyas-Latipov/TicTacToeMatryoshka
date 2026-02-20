package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
data class Pl1(
    var count: MutableState<Int> = mutableIntStateOf(3),
    val color: String = "Red"
)
