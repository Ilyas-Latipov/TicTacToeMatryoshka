package ru.tictac.tictactoe_matryoshka.logic

import androidx.compose.ui.graphics.Color
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.Players
import ru.tictac.tictactoe_matryoshka.models.StartCount
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
    startCount: StartCount,
    button: Players
) {
    when (button.id) {
        "3R" -> {
            startCount.count3R = counter(startCount.count3R)
            button.count.value = startCount.count3R
        }

        "2R" -> {
            startCount.count2R = counter(startCount.count2R)
            button.count.value = startCount.count2R
        }

        "1R" -> {
            startCount.count1R = counter(startCount.count1R)
            button.count.value = startCount.count1R
        }
    }
    when (button.id) {
        "3B" -> {
            startCount.count3B = counter(startCount.count3B)
            button.count.value = startCount.count3B
        }

        "2B" -> {
            startCount.count2B = counter(startCount.count2B)
            button.count.value = startCount.count2B
        }

        "1B" -> {
            startCount.count1B = counter(startCount.count1B)
            button.count.value = startCount.count1B
        }
    }
}

private fun counter(count: Int): Int {
    if (count < 9) {
        return count + 1
    }
    return 0

}