package ru.tictac.tictactoe_matryoshka

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableFloatStateOf
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
        enableEdgeToEdge()
        setContent {
            Oblects()
                }
            }
        }
@Preview(showSystemUi = true)
@Composable
private fun Oblects(){
    val buttonsBoard = mutableListOf(
        BoardModel(1,  "", "" ,""),
        BoardModel(2,  "" ,"", ""),
        BoardModel(3,  "" ,"", ""),
        BoardModel(4,  "" ,"", ""),
        BoardModel(5,  "" ,"", ""),
        BoardModel(6,  "" ,"", ""),
        BoardModel(7,  "" ,"", ""),
        BoardModel(8,  "" ,"", ""),
        BoardModel(9,  "" ,"", ""))
    val pl1Buttons = mutableListOf(Pl1(), Pl1(), Pl1())
    val pl2Buttons = mutableListOf(Pl2(), Pl2(), Pl2())
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(Color.DarkGray),
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            repeat(3) { index ->
                Draw_Pl2(pl2Buttons[index], 70 + index * 2 * 10)
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Фиксированное количество колонок - 3
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Gray),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между колонками
            verticalArrangement = Arrangement.spacedBy(8.dp) // Отступы между рядами
        ) {
            items(buttonsBoard.size) { index ->
                DrawBoard(buttonsBoard[index])
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(Color.DarkGray),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            repeat(3) { index ->
                Draw_Pl1(pl1Buttons[index], 110 - index * 2 * 10)
            }
        }
}}
