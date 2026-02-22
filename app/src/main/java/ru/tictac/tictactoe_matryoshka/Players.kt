package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


interface Players {
    val id: String
    var count: MutableState<Int>
    val color: MutableState<Color>
    var size: MutableState<Int>
    var enabled: MutableState<Boolean>
}

data class Pl1(
    override val id: String,
    override var size: MutableState<Int>,
    override var count: MutableState<Int> = mutableIntStateOf(3),
    override var color: MutableState<Color> = mutableStateOf(Color.Red),
    override var enabled: MutableState<Boolean> = mutableStateOf(true),
) : Players

data class Pl2(
    override val id: String,
    override var size: MutableState<Int>,
    override var count: MutableState<Int> = mutableIntStateOf(3),
    override var color: MutableState<Color> = mutableStateOf(Color.Blue),
    override var enabled: MutableState<Boolean> = mutableStateOf(true),
) : Players
