package ru.tictac.tictactoe_matryoshka.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import ru.tictac.tictactoe_matryoshka.models.SetValue
import ru.tictac.tictactoe_matryoshka.logic.clickPlayer1Button
import ru.tictac.tictactoe_matryoshka.logic.clickPlayer2Button
import ru.tictac.tictactoe_matryoshka.models.Pl1
import ru.tictac.tictactoe_matryoshka.models.Pl2
import ru.tictac.tictactoe_matryoshka.models.Theme

@Composable
fun Draw_Pl1(item: Pl1, set: SetValue, pl1buttons: List<Pl1>, theme: Theme) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(CircleShape)
            .size(item.size.value.dp)
            .background(
                color = if (item.enabled.value)
                    item.color.value
                else
                    theme.themeEnabledNow.value,
            )
            .then(
                if (item.enabled.value) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            color = Color.Black,
                            bounded = false
                        )
                    ) {
                        clickPlayer1Button(item, set, pl1buttons)
                    }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${item.count.value}",
            fontSize = 40.sp,
            fontWeight = FontWeight(550),
            color = Color.White
        )
    }
}


@Composable
fun Draw_Pl2(item: Pl2, set: SetValue, pl2buttons: List<Pl2>, theme: Theme) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(CircleShape)
            .size(item.size.value.dp)
            .background(
                color = if (item.enabled.value)
                    item.color.value
                else
                    theme.themeEnabledNow.value,
            )
            .then(
                if (item.enabled.value) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            color = Color.Black,
                            bounded = false
                        )
                    ) {
                        clickPlayer2Button(item, set, pl2buttons)
                    }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${item.count.value}",
            fontSize = 40.sp,
            fontWeight = FontWeight(550),
            color = Color.White,
            modifier = Modifier.rotate(180f)
        )
    }
}
