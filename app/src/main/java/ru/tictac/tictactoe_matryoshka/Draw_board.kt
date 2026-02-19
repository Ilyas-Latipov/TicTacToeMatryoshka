package ru.tictac.tictactoe_matryoshka

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawBoard(item: BoardModel){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.White),  // Контейнер всегда квадратный
        contentAlignment = Alignment.Center
    ) {
    Button(
        onClick = {
            item.sizeButton.value = 0.3f},
        modifier = Modifier
            .fillMaxSize(item.sizeButton.value)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,      // Цвет фона кнопки
            contentColor = Color.Black,        // Цвет текста/иконок
            disabledContainerColor = Color.Gray, // Цвет когда кнопка неактивна
            disabledContentColor = Color.DarkGray
    ),
    ) {
        Text(text = "Кнопка ${item.id}")
    }
}}