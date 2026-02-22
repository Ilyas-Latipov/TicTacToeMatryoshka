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
fun Draw_Pl2(item: Pl2, click: SetValue) {
    Button(
        onClick = {
            clickPlayerButton(item, click)
        },
        modifier = Modifier
            .padding(vertical = 15.dp)
            .size(item.size.value.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = item.color.value,      // Цвет фона кнопки
            contentColor = Color.DarkGray,        // Цвет текста/иконок
            disabledContainerColor = Color.Gray, // Цвет когда кнопка неактивна
            disabledContentColor = Color.DarkGray
        ),
        enabled = item.enabled.value
    ) {
        Text(
            text = "${item.count.value}",
            fontSize = 45.sp,
            modifier = Modifier.rotate(180f)
        )
    }
}
