package ru.tictac.tictactoe_matryoshka.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import ru.tictac.tictactoe_matryoshka.models.SetValue
import ru.tictac.tictactoe_matryoshka.logic.clickBoardButton
import ru.tictac.tictactoe_matryoshka.models.BoardModel
import ru.tictac.tictactoe_matryoshka.models.GameState
import ru.tictac.tictactoe_matryoshka.models.Pl1
import ru.tictac.tictactoe_matryoshka.models.Pl2
import ru.tictac.tictactoe_matryoshka.models.Theme

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
        Box(
            modifier = Modifier
                .fillMaxSize(item.sizeNow.value)
                .dropShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 5.dp,
                        spread = 2.dp,
                        alpha = 0.5f,
                        color = item.colorNow.value,
                    )
                )
                .background(
                    color = item.colorNow.value,
                    shape = CircleShape
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
                            clickBoardButton(
                                item, set, buttonsBoard,
                                pl1buttons, pl2buttons, state, theme
                            )
                        }
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}