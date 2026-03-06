package ru.tictac.tictactoe_matryoshka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Settings
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import ru.tictac.tictactoe_matryoshka.models.SetValue
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.GameState
import ru.tictac.tictactoe_matryoshka.models.Pl1
import ru.tictac.tictactoe_matryoshka.models.Pl2
import ru.tictac.tictactoe_matryoshka.models.Theme
import ru.tictac.tictactoe_matryoshka.models.ThemeStorage
import ru.tictac.tictactoe_matryoshka.logic.restart
import ru.tictac.tictactoe_matryoshka.ui.DrawBoard
import ru.tictac.tictactoe_matryoshka.ui.Draw_Pl1
import ru.tictac.tictactoe_matryoshka.ui.Draw_Pl2
import ru.tictac.tictactoe_matryoshka.ui.OpenCount
import ru.tictac.tictactoe_matryoshka.ui.OpenSetting
import ru.tictac.tictactoe_matryoshka.ui.OpenTheme
import ru.tictac.tictactoe_matryoshka.ui.SettingButton
import ru.tictac.tictactoe_matryoshka.ui.Winner
import ru.tictac.tictactoe_matryoshka.logic.applyTheme

class MainActivity : ComponentActivity() {
    private lateinit var themeStorage: ThemeStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        themeStorage = ThemeStorage(this)
        enableEdgeToEdge()

        setContent {
            GameApp(themeStorage)
        }
    }
}

@Composable
private fun GameApp(themeStorage: ThemeStorage) {
    val theme = remember { Theme() }
    val buttonsBoard = remember {
        mutableStateListOf(
            BoardModel("1", "1245", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("2", "123456", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("3", "2356", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("4", "124578", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("5", "123456789", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("6", "235689", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("7", "4578", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("8", "456789", mutableStateOf(theme.themeBackNow.value)),
            BoardModel("9", "5689", mutableStateOf(theme.themeBackNow.value))
        )
    }
    // Загрузка последней темы
    LaunchedEffect(Unit) {
        applyTheme(theme, buttonsBoard, themeStorage.isDarkTheme)
    }

    Oblects(
        theme = theme,
        themeStorage = themeStorage,
        buttonsBoard = buttonsBoard
    )
}

@Composable
private fun Oblects(
    theme: Theme,
    themeStorage: ThemeStorage,
    buttonsBoard: List<BoardModel>
) {
    val context = LocalContext.current
    val maxWidth = LocalConfiguration.current.screenWidthDp
    val pl1Buttons = remember {
        mutableListOf(
            Pl1("3R", mutableIntStateOf(90), theme.count3R),
            Pl1("2R", mutableIntStateOf(70), theme.count2R),
            Pl1("1R", mutableIntStateOf(50), theme.count1R)
        )
    }
    val pl2Buttons = remember {
        mutableListOf(
            Pl2("1B", mutableIntStateOf(50), theme.count1B),
            Pl2("2B", mutableIntStateOf(70), theme.count2B),
            Pl2("3B", mutableIntStateOf(90), theme.count3B)
        )
    }
    val setValue = remember { SetValue() }
    val state = remember { mutableStateOf<GameState>(GameState.Playing) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.themeBackNow.value),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        // Верхний ряд Pl2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(3) { index ->
                Draw_Pl2(pl2Buttons[index], setValue, pl2Buttons, theme, state)
            }
        }

        // Игровое поле
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height((maxWidth * 0.94).dp)
                .padding(start = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameField(
                buttonsBoard = buttonsBoard,
                setValue = setValue,
                pl1Buttons = pl1Buttons,
                pl2Buttons = pl2Buttons,
                state = state,
                theme = theme
            )

            SettingButton(
                backgroundColor = Color.Yellow,
                icon = Icons.Default.Settings,
                iconContentDescription = "Настройки",
                width = (maxWidth - maxWidth * 0.94).dp,
                height = (maxWidth / 1.5).dp,
                pading = 3.dp,
                iconSize = 0.9f,
                onClick = {
                    vibrate(context, 40)
                    state.value = GameState.Settings
                }
            )
        }

        // Нижний ряд Pl1
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(16.dp)
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
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(3) { index ->
                Draw_Pl1(pl1Buttons[index], setValue, pl1Buttons, theme, state)
            }
        }
    }

    // Обработка состояний
    when (state.value) {
        GameState.Playing -> {}
        GameState.Settings -> {
            OpenSetting(theme, state)
        }

        GameState.Restart -> {
            restart(state, buttonsBoard, pl1Buttons, pl2Buttons, setValue, theme)
        }

        GameState.Theme -> {
            OpenTheme(theme, state, buttonsBoard, themeStorage)
        }

        GameState.Count -> {
            OpenCount(theme, state, pl1Buttons, pl2Buttons)
        }

        GameState.Help -> {TODO()}
        is GameState.GameOver -> {
            vibrate(context, 100)
            Winner(
                theme,
                state,
                (state.value as GameState.GameOver).winners
            )
        }
    }
}

@Composable
fun GameField(
    buttonsBoard: List<BoardModel>,
    setValue: SetValue,
    pl1Buttons: List<Pl1>,
    pl2Buttons: List<Pl2>,
    state: MutableState<GameState>,
    theme: Theme
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = theme.themeShapeRadiusNow.value.dp,
                    spread = 2.dp,
                    alpha = 0.4f,
                    color = theme.themeShapeNow.value,
                )
            )
            .background(Color(0xFFFFBF00))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            for (row in 0..2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (col in 0..2) {
                        val index = row * 3 + col
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(3.dp)
                        ) {
                            DrawBoard(
                                buttonsBoard[index],
                                setValue,
                                buttonsBoard,
                                pl1Buttons,
                                pl2Buttons,
                                state,
                                theme
                            )
                        }
                    }
                }
            }
        }
    }
}