package ru.tictac.tictactoe_matryoshka.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.RepeatMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.platform.LocalConfiguration
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.GameState
import ru.tictac.tictactoe_matryoshka.models.Theme
import ru.tictac.tictactoe_matryoshka.models.ThemeStorage


@Composable
fun SettingButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    iconColor: Color = Color.Black,
    icon: ImageVector,
    iconContentDescription: String? = null,
    width: Dp = (LocalConfiguration.current.screenWidthDp / 7.2).dp,
    height: Dp = (LocalConfiguration.current.screenWidthDp / 7.2).dp,
    pading: Dp = 15.dp,
    iconSize: Float = 0.5f,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(pading)
            .size(width, height)
            .clip(RoundedCornerShape(percent = 40))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
            tint = iconColor,
            modifier = Modifier
                .rotate(90f)
                .fillMaxSize(iconSize)
        )
    }
}


@Composable
fun OpenSetting(
    theme: Theme,
    state: MutableState<GameState>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 3.dp)
                .dropShadow(
                    shape = RoundedCornerShape(percent = 40),
                    shadow = Shadow(
                        radius = theme.themeShapeRadiusNow.value.dp,
                        spread = 1.dp,
                        alpha = 0.4f,
                        color = theme.themeShapeNow.value,
                    )
                )
                .background(
                    theme.themeRowNow.value,
                    shape = RoundedCornerShape(percent = 40)
                ),
        ) {
            SettingButton(
                backgroundColor = Color.Red,
                icon = Icons.Default.Autorenew,
                iconContentDescription = "Перезапустить",
                onClick = {
                    state.value = GameState.Restart
                }
            )
            SettingButton(
                backgroundColor = Color.Yellow,
                icon = Icons.Default.ColorLens,
                iconContentDescription = "Сменить тему",
                onClick = {
                    state.value = GameState.Theme
                }
            )
            SettingButton(
                backgroundColor = Color.Green,
                icon = Icons.Default.QuestionMark,
                iconContentDescription = "Помощь",
                onClick = {
                    state.value = GameState.Help
                }
            )
            SettingButton(
                backgroundColor = Color.Blue,
                icon = Icons.Default.Close,
                iconContentDescription = "Продолжить игру",
                onClick = {
                    state.value = GameState.Playing
                }
            )
        }

    }
}

@Composable
fun OpenTheme(
    theme: Theme,
    state: MutableState<GameState>,
    buttonsBoard: List<BoardModel>,
    themeStorage: ThemeStorage
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 3.dp)
                .dropShadow(
                    shape = RoundedCornerShape(percent = 40),
                    shadow = Shadow(
                        radius = theme.themeShapeRadiusNow.value.dp,
                        spread = 1.dp,
                        alpha = 0.4f,
                        color = theme.themeShapeNow.value,
                    )
                )
                .background(
                    theme.themeRowNow.value,
                    shape = RoundedCornerShape(percent = 40)
                )
        ) {
            SettingButton(
                backgroundColor = theme.whiteThemeBack,
                icon = Icons.Default.WbSunny,
                iconContentDescription = "Светлая тема",
                onClick = {
                    applyTheme(theme, buttonsBoard, false)
                    themeStorage.isDarkTheme = false
                    state.value = GameState.Settings
                }
            )
            SettingButton(
                backgroundColor = theme.blackThemeBack,
                iconColor = Color.White,
                icon = Icons.Default.DarkMode,
                iconContentDescription = "Темная тема",
                onClick = {
                    applyTheme(theme, buttonsBoard, true)
                    themeStorage.isDarkTheme = true
                    state.value = GameState.Settings
                }
            )
            SettingButton(
                backgroundColor = Color.Blue,
                icon = Icons.Default.Close,
                iconContentDescription = "Назад",
                onClick = {
                    state.value = GameState.Settings
                }
            )
        }
    }
}

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

@Composable
fun Winner(
    theme: Theme,
    state: MutableState<GameState>,
    winners: List<Color>
) {
    // Анимация мерцания
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha = infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        )
    )
    val isDraw = winners.contains(Color.Red) && winners.contains(Color.Blue)
    val redWins = winners.contains(Color.Red) && !winners.contains(Color.Blue)
    val blueWins = winners.contains(Color.Blue) && !winners.contains(Color.Red)

    // Цвет для верхнего бокса (синие)
    val topBoxColor = when {
        isDraw -> Color.Magenta.copy(alpha = glowAlpha.value)  // Ничья - оба пурпурные
        blueWins -> Color.Blue.copy(alpha = glowAlpha.value)  // Только синие
        else -> theme.themeEnabledNow.value.copy(alpha = 0.5f)
    }

    // Цвет для нижнего бокса (красные)
    val bottomBoxColor = when {
        isDraw -> Color.Magenta.copy(alpha = glowAlpha.value)  // Ничья - оба пурпурные
        redWins -> Color.Red.copy(alpha = glowAlpha.value)  // Только красные
        else -> theme.themeEnabledNow.value.copy(alpha = 0.5f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(topBoxColor)
                .pointerInput(Unit) {}
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(theme.themeRowNow.value)
                .pointerInput(Unit) {}
        ) {
            SettingButton(
                backgroundColor = Color.Yellow,
                icon = Icons.Default.Autorenew,
                iconContentDescription = "Перезапустить",
                width = LocalConfiguration.current.screenWidthDp.dp,
                height = 70.dp,
                onClick = {
                    state.value = GameState.Restart
                }
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(bottomBoxColor)
                .pointerInput(Unit) {}
        )
    }
}