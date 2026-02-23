package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

data class SetValue(
    var id: String = "",
    var oldBoardId: String = "",
    var whoWalk: Boolean = true, // true -> Red | false -> Blue
    var selected: Boolean = false,
    var color: MutableState<Color> = mutableStateOf(Color.Unspecified),
    var size: MutableState<Float> = mutableFloatStateOf(0f)
)
