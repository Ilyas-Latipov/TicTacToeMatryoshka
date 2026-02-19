package ru.tictac.tictactoe_matryoshka

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tictac.tictactoe_matryoshka.ui.theme.TicTacToe_matryoshkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListItem()
                }
            }
        }
@Preview(showSystemUi = true)
@Composable
private fun ListItem(){
    val buttons = mutableListOf(
        BoardModel(1,  "", "" ,""),
        BoardModel(2,  "" ,"", ""),
        BoardModel(3,  "" ,"", ""),
        BoardModel(4,  "" ,"", ""),
        BoardModel(5,  "" ,"", ""),
        BoardModel(6,  "" ,"", ""),
        BoardModel(7,  "" ,"", ""),
        BoardModel(8,  "" ,"", ""),
        BoardModel(9,  "" ,"", ""))

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Фиксированное количество колонок - 3
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Gray),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между колонками
        verticalArrangement = Arrangement.spacedBy(8.dp) // Отступы между рядами
    ) {
        items(buttons.size) { index ->
            DrawBoard(buttons[index])
        }
    }
}
