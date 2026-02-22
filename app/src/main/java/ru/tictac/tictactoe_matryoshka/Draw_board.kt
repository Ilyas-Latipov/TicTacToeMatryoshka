package ru.tictac.tictactoe_matryoshka

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun DrawBoard(item: BoardModel, click: SetValue) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.White),  // Контейнер всегда квадратный
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (click.selected){clickBoardButtonSet(item, click)}
                else {clickBoardButtonRearrange(item, click)}
            },
            modifier = Modifier
                .fillMaxSize(item.sizeNow.value)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                containerColor = item.colorNow.value,      // Цвет фона кнопки
                contentColor = Color.Black,        // Цвет текста/иконок
                disabledContainerColor = Color.Gray, // Цвет когда кнопка неактивна
                disabledContentColor = Color.DarkGray
            ),
        ) {
            Text(text = "Кнопка ${item.id}")
        }
    }
}