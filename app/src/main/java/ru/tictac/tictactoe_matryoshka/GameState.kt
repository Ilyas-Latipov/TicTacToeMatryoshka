package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
sealed class GameState {
    object Playing : GameState()
    object Settings : GameState()
    object Restart : GameState()
    data class Teme(var teme: Boolean) : GameState()
    data class GameOver(var winner: String) : GameState()
}


data class Theme(
    val whiteThemeBack: Color = Color(0xFFF0EAD6),
    val blackThemeBack: Color = Color(0xFF1C1C1C),
    val whiteThemeRow: Color = Color.DarkGray,
    val blackThemeRow: Color = Color.Gray,
    val whiteThemeEnabled: Color = Color.Gray,
    val blackThemeEnabled: Color = Color(0xFFCCCCCC),
    var themeBackNow: MutableState<Color> = mutableStateOf(whiteThemeBack),
    var themeRowNow: MutableState<Color> = mutableStateOf(whiteThemeRow),
    var themeEnabledNow: MutableState<Color> = mutableStateOf(whiteThemeEnabled)
)