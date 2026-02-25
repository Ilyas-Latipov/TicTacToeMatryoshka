package ru.tictac.tictactoe_matryoshka

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Draw_Pl1(item: Pl1, set: SetValue, pl1buttons: List<Pl1>, theme: Theme) {
    Button(
        onClick = {
            clickPlayer1Button(item, set, pl1buttons)
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(item.size.value.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = item.color.value,
            contentColor = Color.White,
            disabledContainerColor = theme.themeEnabledNow.value,
            disabledContentColor = Color.White
        ),
        enabled = item.enabled.value
    ) {
        Text(
            text = "${item.count.value}",
            fontSize = 30.sp
        )
    }
}


@Composable
fun Draw_Pl2(item: Pl2, set: SetValue, pl2buttons: List<Pl2>, theme: Theme) {
    Button(
        onClick = {
            clickPlayer2Button(item, set, pl2buttons)
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(item.size.value.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = item.color.value,
            contentColor = Color.White,
            disabledContainerColor = theme.themeEnabledNow.value,
            disabledContentColor = Color.White
        ),
        enabled = item.enabled.value
    ) {
        Text(
            text = "${item.count.value}",
            fontSize = 30.sp,
            modifier = Modifier.rotate(180f)
        )
    }
}
