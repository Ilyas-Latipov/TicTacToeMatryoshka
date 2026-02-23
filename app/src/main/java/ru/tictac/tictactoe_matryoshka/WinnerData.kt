package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WinnerData (
    var winner: MutableState<String> = mutableStateOf("")

)