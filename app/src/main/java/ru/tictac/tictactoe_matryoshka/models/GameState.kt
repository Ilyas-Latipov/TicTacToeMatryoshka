package ru.tictac.tictactoe_matryoshka.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
sealed class GameState {
    object Playing : GameState()
    object Settings : GameState()
    object Restart : GameState()
    object Theme : GameState()
    object Count : GameState()
    object Help : GameState()
    data class GameOver(val winners: List<Color>) : GameState()
}


data class Theme(
    val whiteThemeBack: Color = Color(0xFFE6E6E6),
    val whiteThemeRow: Color = Color(0xFFB9B9B9),
    val whiteThemeEnabled: Color = Color(0xFF8C8C8C),
    val blackThemeBack: Color = Color(0xFF252525),
    val blackThemeRow: Color = Color(0xFF464646),
    val blackThemeEnabled: Color = Color(0xFF737373),
    var themeShapeRadiusNow: MutableState<Int> = mutableIntStateOf(1),
    var themeShapeNow: MutableState<Color> = mutableStateOf(Color.White),
    var themeBackNow: MutableState<Color> = mutableStateOf(whiteThemeBack),
    var themeRowNow: MutableState<Color> = mutableStateOf(whiteThemeRow),
    var themeEnabledNow: MutableState<Color> = mutableStateOf(whiteThemeEnabled)
)

data class StartCount(
    var count1R: Int = 3,
    var count2R: Int = 3,
    var count3R: Int = 3,
    var count1B: Int = 3,
    var count2B: Int = 3,
    var count3B: Int = 3
)