package ru.tictac.tictactoe_matryoshka

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val buttonsBoard = remember {
        mutableListOf(
            BoardModel("1", "1245"),
            BoardModel("2", "123456"),
            BoardModel("3", "2356"),
            BoardModel("4", "124578"),
            BoardModel("5", "123456789"),
            BoardModel("6", "235689"),
            BoardModel("7", "4578"),
            BoardModel("8", "456789"),
            BoardModel("9", "5689")
        )
    }
    val pl1Buttons = remember {
        mutableListOf(
            Pl1("3R", mutableIntStateOf(110)),
            Pl1("2R", mutableIntStateOf(90)),
            Pl1("1R", mutableIntStateOf(70))
        )
    }
    val pl2Buttons = remember {
        mutableListOf(
            Pl2("1B", mutableIntStateOf(70)),
            Pl2("2B", mutableIntStateOf(90)),
            Pl2("3B", mutableIntStateOf(110))
        )
    }
    val allPlayersButtons = pl1Buttons + pl2Buttons
    val winner = WinnerData()
    val setValue = SetValue()
    var colorRow1 = remember { mutableStateOf(Color.DarkGray) }
    var colorRow2 = remember { mutableStateOf(Color.Gray) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(colorRow2.value),
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            repeat(3) { index ->
                Draw_Pl2(pl2Buttons[index], setValue, allPlayersButtons)
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Фиксированное количество колонок - 3
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFBF00)),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между колонками
            verticalArrangement = Arrangement.spacedBy(8.dp) // Отступы между рядами
        ) {
            items(buttonsBoard.size) { index ->
                DrawBoard(
                    buttonsBoard[index],
                    setValue,
                    buttonsBoard,
                    allPlayersButtons,
                    winner
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(colorRow1.value),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            repeat(3) { index ->
                Draw_Pl1(pl1Buttons[index], setValue, allPlayersButtons)
            }
        }
    }
    Winner(winner, buttonsBoard, pl1Buttons, pl2Buttons, setValue, colorRow1, colorRow2)
}
