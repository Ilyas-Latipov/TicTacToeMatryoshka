package ru.tictac.tictactoe_matryoshka.logic

import androidx.compose.ui.graphics.Color
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.Theme
import kotlin.collections.forEach

// Функция применения темы ко всем элементам
fun applyTheme(
    theme: Theme,
    buttonsBoard: List<BoardModel>,
    isDark: Boolean
) {
    val newBack = if (isDark) theme.blackThemeBack else theme.whiteThemeBack
    val newEnabled = if (isDark) theme.blackThemeEnabled else theme.whiteThemeEnabled

    buttonsBoard.forEach {
        when (it.buttonBackground.value) {
            theme.themeBackNow.value -> it.buttonBackground.value = newBack
            theme.themeEnabledNow.value -> it.buttonBackground.value = newEnabled
        }
    }
    theme.themeShapeNow.value = if (isDark) Color.White else Color.Black
    theme.themeShapeRadiusNow.value = if (isDark) 1 else 3
    theme.themeRowNow.value = if (isDark) theme.blackThemeRow else theme.whiteThemeRow
    theme.themeBackNow.value = newBack
    theme.themeEnabledNow.value = newEnabled
}

fun countEditor(
    theme: Theme,
    id: String
) {
    when (id) {
        "3R" -> theme.count3R.value = counter(theme.count3R.value)
        "2R" -> theme.count2R.value = counter(theme.count2R.value)
        "1R" -> theme.count1R.value = counter(theme.count1R.value)
    }
    when (id) {
        "3B" -> theme.count3B.value = counter(theme.count3B.value)
        "2B" -> theme.count2B.value = counter(theme.count2B.value)
        "1B" -> theme.count1B.value = counter(theme.count1B.value)
    }
}

fun counter(count: Int): Int {
    if (count < 9) {
        return count + 1
    } else {
        return 0
    }
}