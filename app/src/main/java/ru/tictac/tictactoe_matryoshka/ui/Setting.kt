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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Text
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.GameState
import ru.tictac.tictactoe_matryoshka.models.Pl1
import ru.tictac.tictactoe_matryoshka.models.Pl2
import ru.tictac.tictactoe_matryoshka.models.Theme
import ru.tictac.tictactoe_matryoshka.models.ThemeStorage
import ru.tictac.tictactoe_matryoshka.logic.applyTheme
import ru.tictac.tictactoe_matryoshka.vibrate


@Composable
fun SettingButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    iconColor: Color = Color.Black,
    icon: ImageVector,
    iconContentDescription: String? = null,
    width: Dp = (LocalConfiguration.current.screenWidthDp / 7.2).dp,
    height: Dp = (LocalConfiguration.current.screenWidthDp / 7.2).dp,
    rotate: Float = 90f,
    pading: Dp = 12.dp,
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
                .rotate(rotate)
                .fillMaxSize(iconSize)
        )
    }
}

@Composable
fun HelpRow(
    theme: Theme,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, end = 5.dp, bottom = 10.dp)
            .dropShadow(
                shape = RoundedCornerShape(percent = 45),
                shadow = Shadow(
                    radius = theme.themeShapeRadiusNow.value.dp,
                    spread = 1.dp,
                    alpha = 0.4f,
                    color = theme.themeShapeNow.value,
                )
            )
            .background(
                theme.themeRowNow.value,
                shape = RoundedCornerShape(percent = 45)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) { content() }
}

@Composable
fun HelpBox(
    theme: Theme,
    text: String,
    top: Dp = 0.dp,
    bottom: Dp = 20.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = top, bottom = bottom)
            .dropShadow(
                shape = RoundedCornerShape(percent = 10),
                shadow = Shadow(
                    radius = theme.themeShapeRadiusNow.value.dp,
                    spread = 1.dp,
                    alpha = 0.4f,
                    color = theme.themeShapeNow.value,
                )
            )
            .background(
                theme.themeRowNow.value,
                shape = RoundedCornerShape(percent = 10)
            ),
    ) {
        Text(
            text = text,
            color = theme.themeShapeNow.value,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun OpenSetting(
    theme: Theme,
    state: MutableState<GameState>
) {
    val context = LocalContext.current
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
                ),
        ) {
            SettingButton(
                backgroundColor = Color.Red,
                icon = Icons.Default.Autorenew,
                iconContentDescription = "Перезапустить",
                onClick = {
                    vibrate(context, 35)
                    state.value = GameState.Restart
                }
            )
            SettingButton(
                backgroundColor = Color.Yellow,
                icon = Icons.Default.ColorLens,
                iconContentDescription = "Сменить тему",
                onClick = {
                    vibrate(context, 35)
                    state.value = GameState.Theme
                }
            )

            SettingButton(
                backgroundColor = Color.Green,
                icon = Icons.Default.QuestionMark,
                iconContentDescription = "Помощь",
                onClick = {
                    vibrate(context, 35)
                    state.value = GameState.Help
                }
            )
            SettingButton(
                backgroundColor = Color(0xFFFF9800),
                icon = Icons.Default.Animation,
                iconContentDescription = "Сменить количество фигур",
                onClick = {
                    vibrate(context, 35)
                    state.value = GameState.Count
                }
            )
            SettingButton(
                backgroundColor = Color.Blue,
                icon = Icons.Default.Close,
                iconContentDescription = "Продолжить игру",
                onClick = {
                    vibrate(context, 35)
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
    val context = LocalContext.current
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
                    vibrate(context, 35)
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
                    vibrate(context, 35)
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
                    vibrate(context, 35)
                    state.value = GameState.Settings
                }
            )
        }
    }
}


@Composable
fun OpenCount(
    theme: Theme,
    state: MutableState<GameState>,
    pl1buttons: List<Pl1>,
    pl2buttons: List<Pl2>
) {
    val context = LocalContext.current
    val maxWidth = LocalConfiguration.current.screenWidthDp
    pl1buttons.forEach { it.enabled.value = true }
    pl2buttons.forEach { it.enabled.value = true }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxWidth.dp)
                .background(theme.themeBackNow.value)
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {
            SettingButton(
                backgroundColor = Color.Yellow,
                icon = Icons.Default.Autorenew,
                iconContentDescription = "Перезапустить",
                width = maxWidth.dp,
                height = 70.dp,
                onClick = {
                    vibrate(context, 40)
                    state.value = GameState.Restart
                }
            )
        }
    }
}

@Composable
fun OpenHelp(
    theme: Theme,
    state: MutableState<GameState>
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.themeBackNow.value)
    ) {
        item {
            HelpBox(
                theme,
                "Побеждает тот игрок который выстроил из 3 фигур своего цвета " +
                        "ряд или диагональ. Большие фигуры могут крыть меньшие. Игрок может переставлять " +
                        "свои фигуры на соседние клетки (нажимая на фигурку своего цвета). Первыми " +
                        "ходят красные. У игроков есть ограниченное число фигур каждого размера " +
                        "(по умолчанию по 3). Пока игрок не поставил фигуру на поле ход можно отменить.",
                40.dp, 40.dp
            )
        }
        item {
            HelpRow(
                theme = theme,
                content = {
                    SettingButton(
                        backgroundColor = Color.Red,
                        icon = Icons.Default.Autorenew,
                        iconContentDescription = "Перезапустить",
                        width = 70.dp,
                        height = 70.dp,
                        rotate = 0f,
                        pading = 8.dp,
                        onClick = {}
                    )
                    Text(
                        text = "Перезапустить игру.",
                        color = theme.themeShapeNow.value,
                        fontSize = 20.sp
                    )
                }
            )
            HelpBox(
                theme, "При нажатии происходит перезапуск партии. Тема игры и количество" +
                        " фигур при начале игры сохряняется."
            )
        }
        item {
            HelpRow(
                theme = theme,
                content = {
                    SettingButton(
                        backgroundColor = Color.Yellow,
                        icon = Icons.Default.ColorLens,
                        iconContentDescription = "Сменить тему",
                        width = 70.dp,
                        height = 70.dp,
                        rotate = 0f,
                        pading = 8.dp,
                        onClick = {}
                    )
                    Text(
                        text = "Сменить тему.",
                        color = theme.themeShapeNow.value,
                        fontSize = 20.sp
                    )
                }
            )
            HelpBox(
                theme, "При нажатии появляется окошко с темами. Нажав на тему цветовая " +
                        "палитра игры изменится."
            )
        }
        item {
            HelpRow(
                theme = theme,
                content = {
                    SettingButton(
                        backgroundColor = Color(0xFFFF9800),
                        icon = Icons.Default.Animation,
                        iconContentDescription = "Сменить количество фигур",
                        width = 70.dp,
                        height = 70.dp,
                        rotate = 0f,
                        pading = 8.dp,
                        onClick = {}
                    )
                    Text(
                        text = "Сменить количество фигур.",
                        color = theme.themeShapeNow.value,
                        fontSize = 20.sp
                    )
                }
            )
            HelpBox(
                theme, "При нажатии можно настраивать количество фигур в начале игры. " +
                        "Нажимайте на любую фигурку и количество данной фигуры увеличится на 1. " +
                        "Количество фигур настраивается от 0 до 9. Если количество фигур 9, то при " +
                        "следующем нажатии количество фигур станет 0."
            )
        }
        item {
            HelpRow(
                theme = theme,
                content = {
                    SettingButton(
                        backgroundColor = Color.Blue,
                        icon = Icons.Default.Close,
                        iconContentDescription = "Продолжить игру",
                        width = 70.dp,
                        height = 70.dp,
                        rotate = 0f,
                        pading = 8.dp,
                        onClick = {}
                    )
                    Text(
                        text = "Вернуться обратно.",
                        color = theme.themeShapeNow.value,
                        fontSize = 20.sp
                    )
                }
            )
            HelpBox(theme, "Возвращает вас назад в настройках.")
        }
        item {
            SettingButton(
                backgroundColor = Color.Blue,
                icon = Icons.Default.Close,
                iconContentDescription = "Продолжить игру",
                width = LocalConfiguration.current.screenWidthDp.dp,
                height = 50.dp,
                onClick = {
                    vibrate(context, 35)
                    state.value = GameState.Settings
                }
            )
        }
    }
}


@Composable
fun Winner(
    theme: Theme,
    state: MutableState<GameState>,
    winners: List<Color>
) {
    val context = LocalContext.current
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
                    vibrate(context, 40)
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