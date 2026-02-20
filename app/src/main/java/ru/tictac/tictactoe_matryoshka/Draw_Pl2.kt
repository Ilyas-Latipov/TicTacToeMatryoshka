package ru.tictac.tictactoe_matryoshka

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Draw_Pl2(item: Pl2, size: Int){
    Button(
        onClick = {if (item.count.value != 0){item.count.value -= 1}},
        modifier = Modifier
            .padding(vertical = 15.dp)
            .size(size.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,      // Цвет фона кнопки
            contentColor = Color.DarkGray,        // Цвет текста/иконок
            disabledContainerColor = Color.Gray, // Цвет когда кнопка неактивна
            disabledContentColor = Color.DarkGray
        ),
    ) {
        Text(text ="${item.count.value}",
            fontSize = 45.sp,
            modifier = Modifier.rotate(180f))
    }
}
