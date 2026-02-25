package ru.tictac.tictactoe_matryoshka

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
        enableEdgeToEdge()
        setContent {
            Oblects()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Oblects() {
    val theme = Theme()
    val buttonsBoard = remember {
        mutableListOf(
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
    val pl1Buttons = remember {
        mutableListOf(
            Pl1("3R", mutableIntStateOf(105)),
            Pl1("2R", mutableIntStateOf(85)),
            Pl1("1R", mutableIntStateOf(65))
        )
    }
    val pl2Buttons = remember {
        mutableListOf(
            Pl2("1B", mutableIntStateOf(65)),
            Pl2("2B", mutableIntStateOf(85)),
            Pl2("3B", mutableIntStateOf(105))
        )
    }
    val setValue = SetValue()
    var state = remember { mutableStateOf<GameState>(GameState.Playing) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.themeBackNow.value),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(theme.themeRowNow.value),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(3) { index ->
                Draw_Pl2(pl2Buttons[index], setValue, pl2Buttons, theme)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(theme.themeBackNow.value)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // Фиксированное количество колонок - 3
                modifier = Modifier
                    .weight(0.92f)
                    .padding(horizontal = 3.dp)
                    .background(Color(0xFFFFBF00)),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между колонками
                verticalArrangement = Arrangement.spacedBy(8.dp) // Отступы между рядами
            ) {
                items(buttonsBoard.size) { index ->
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
            Button(
                onClick = {},
                modifier = Modifier.weight(0.06f).height(330.dp).padding(end = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.White
                )){}
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(theme.themeRowNow.value),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            repeat(3) { index ->
                Draw_Pl1(pl1Buttons[index], setValue, pl1Buttons, theme)
            }
        }
    }
}
