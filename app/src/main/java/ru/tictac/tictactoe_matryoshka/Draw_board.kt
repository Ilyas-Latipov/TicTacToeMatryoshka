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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawBoard(
    item: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    pl1buttons: List<Pl1>,
    pl2buttons: List<Pl2>,
    state: MutableState<GameState>,
    theme: Theme
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(item.buttonBackground.value),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                clickBoardButton(item, set, buttonsBoard, pl1buttons, pl2buttons, state, theme)
            },
            modifier = Modifier
                .fillMaxSize(item.sizeNow.value)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                containerColor = item.colorNow.value,
                disabledContainerColor = item.colorNow.value,
            ),
            enabled = item.enabled.value
        ) {}
    }
}